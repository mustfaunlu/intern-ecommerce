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
import com.mustafaunlu.ecommerce.utils.observeTextChanges
import com.mustafaunlu.ecommerce.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSearchViewTextChanges()
        homeViewModel.products.observe(viewLifecycleOwner) {
            when (it) {
                is ScreenState.Error -> {
                }
                is ScreenState.Loading -> {
                }
                is ScreenState.Success -> {
                    binding.productRv.adapter = ProductAdapter { productId ->
                        navigateToProductDetail(productId)
                    }
                    (binding.productRv.adapter as ProductAdapter).submitList(it.uiData)
                }
            }
        }

        homeViewModel.categories.observe(viewLifecycleOwner) { homepageState ->
            when (homepageState) {
                is ScreenState.Error -> {
                    requireView().showToast(homepageState.message)
                }
                is ScreenState.Loading -> { // progress bar
                }
                is ScreenState.Success -> {
                    binding.rvCategory.adapter = CategoryAdapter(
                        homepageState.uiData,
                    ) { categoryName ->
                        getProductsByCategoryName(categoryName)
                    }
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
            .debounce(300L)
            .distinctUntilChanged()
            .onEach {
                if (it.isBlank()) {
                    homeViewModel.getAllProducts()
                } else {
                    homeViewModel.searchProduct(it)
                }
            }.launchIn(lifecycleScope)
    }
}
