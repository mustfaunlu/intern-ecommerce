package com.mustafaunlu.ecommerce.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.databinding.FragmentHomeBinding
import com.mustafaunlu.ecommerce.utils.checkInternetConnection
import com.mustafaunlu.ecommerce.utils.getUserIdFromSharedPref
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.observeTextChanges
import com.mustafaunlu.ecommerce.utils.showBadgeVisibility
import com.mustafaunlu.ecommerce.utils.showToast
import com.mustafaunlu.ecommerce.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        productAdapter = ProductAdapter(::navigateToProductDetail)
        binding.homeProductRv?.adapter = productAdapter
        observeSearchViewTextChanges()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        setObservers()
        homeViewModel.getBadgeState(getUserIdFromSharedPref(sharedPref))
    }

    private fun setObservers() {
        homeViewModel.badge.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Error -> {
                    binding.homeProgressBar?.gone()
                    requireView().showToast(it.message)
                }

                is ScreenState.Loading -> {
                    binding.homeProgressBar?.visible()
                }

                is ScreenState.Success -> {
                    showBadgeVisibility(it.uiData.hasBadge)
                    binding.homeProgressBar?.gone()
                }
            }
        }
        homeViewModel.products.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Error -> {
                    binding.homeProgressBar?.gone()
                    requireView().showToast(it.message)
                }

                ScreenState.Loading -> {
                    binding.homeProgressBar?.visible()
                }

                is ScreenState.Success -> {
                    productAdapter.submitList(it.uiData)
                    binding.homeProgressBar?.gone()
                }
            }
        }

        homeViewModel.categories.observe(viewLifecycleOwner) { homepageState ->
            when (homepageState) {
                is ScreenState.Error -> {
                    binding.homeProgressBar?.gone()
                    requireView().showToast(homepageState.message)
                }

                is ScreenState.Loading -> {
                    binding.homeProgressBar?.visible()
                }

                is ScreenState.Success -> {
                    binding.homeCategoryRv?.adapter =
                        CategoryAdapter(homepageState.uiData) { categoryName ->
                            getProductsByCategoryName(categoryName)
                        }
                    binding.homeProgressBar?.gone()
                }
            }
        }
    }

    private fun navigateToProductDetail(productId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(productId)
        findNavController().navigate(action)
    }

    private fun getProductsByCategoryName(categoryName: String) {
        homeViewModel.getProductsByCategory(categoryName)
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchViewTextChanges() {
        binding.searchEditText.observeTextChanges()
            .filter { it.isNotBlank() }
            .debounce(DURATION_MS_FLOW_DEBOUNCE)
            .distinctUntilChanged()
            .onEach {
                homeViewModel.searchProduct(it)
            }.launchIn(lifecycleScope)
    }

    companion object {
        private const val DURATION_MS_FLOW_DEBOUNCE = 300L
    }
}
