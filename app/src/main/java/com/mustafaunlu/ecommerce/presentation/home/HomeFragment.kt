package com.mustafaunlu.ecommerce.presentation.home

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
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.observeTextChanges
import com.mustafaunlu.ecommerce.utils.showToast
import com.mustafaunlu.ecommerce.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        productAdapter = ProductAdapter(::navigateToProductDetail)
        binding.productRv.adapter = productAdapter
        observeSearchViewTextChanges()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        setObservers()
    }

    private fun setObservers() {
        homeViewModel.products.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Error -> {
                    binding.progressBar.gone()
                    requireView().showToast(it.message)
                }

                ScreenState.Loading -> {
                    binding.progressBar.visible()
                }

                is ScreenState.Success -> {
                    productAdapter.submitList(it.uiData)
                    binding.progressBar.gone()
                }
            }
        }

        homeViewModel.categories.observe(viewLifecycleOwner) { homepageState ->
            when (homepageState) {
                is ScreenState.Error -> {
                    binding.progressBar.gone()
                    requireView().showToast(homepageState.message)
                }

                is ScreenState.Loading -> {
                    binding.progressBar.visible()
                }

                is ScreenState.Success -> {
                    binding.rvCategory.adapter = CategoryAdapter(homepageState.uiData) { categoryName ->
                        getProductsByCategoryName(categoryName)
                    }
                    binding.progressBar.gone()
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
            .debounce(300L)
            .distinctUntilChanged()
            .onEach {
                homeViewModel.searchProduct(it)
            }.launchIn(lifecycleScope)
    }
}
