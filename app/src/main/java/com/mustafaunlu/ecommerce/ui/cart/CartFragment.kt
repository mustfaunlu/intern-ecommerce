package com.mustafaunlu.ecommerce.ui.cart

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.databinding.FragmentCartBinding
import com.mustafaunlu.ecommerce.domain.entity.cart.UserCartBadgeEntity
import com.mustafaunlu.ecommerce.utils.checkInternetConnection
import com.mustafaunlu.ecommerce.utils.getUserIdFromSharedPref
import com.mustafaunlu.ecommerce.utils.gone
import com.mustafaunlu.ecommerce.utils.showBadgeVisibility
import com.mustafaunlu.ecommerce.utils.showConfirmationDialog
import com.mustafaunlu.ecommerce.utils.showToast
import com.mustafaunlu.ecommerce.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CartViewModel by viewModels()
    private lateinit var adapter: CartListAdapter
    private lateinit var totalPrice: String

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val userId = getUserIdFromSharedPref(sharedPref)
        viewModel.getCartsByUserId(userId)
        adapter = CartListAdapter(
            ::onItemLongClicked,
            ::updateTotalPriceInAdapter,
            ::updateCartItemQuantity,
            ::onItemShortClicked
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        setupObserver()

        binding.btnBuyNow.setOnClickListener {
            requireView().showToast("Under development")
        }
    }

    private fun setupObserver() {
        viewModel.totalPriceLiveData.observe(viewLifecycleOwner) { totalPrice ->
            this.totalPrice = totalPrice.toString()
            binding.tvTotalAmount.text = totalPrice.toString()
        }

        viewModel.userCarts.observe(viewLifecycleOwner) { userCartState ->
            when (userCartState) {
                is ScreenState.Error -> {
                    binding.cartProgressBar.gone()
                    requireView().showToast(userCartState.message)
                }

                ScreenState.Loading -> binding.cartProgressBar.visible()
                is ScreenState.Success -> {
                    binding.cartProgressBar.gone()
                    adapter.submitList(userCartState.uiData)
                    binding.rvCartProducts.adapter = adapter
                }
            }
        }
    }

    private fun updateTotalPriceInAdapter() {
        val cartList = adapter.currentList
        val totalPrice = viewModel.calculateTotalPrice(cartList)
        viewModel.updateTotalPrice(totalPrice)
    }

    private fun updateCartItemQuantity(userCartUiData: UserCartUiData) {
        viewModel.updateUserCartItem(userCartUiData)
    }

    private fun onItemLongClicked(userCartUiData: UserCartUiData) {
        this.showConfirmationDialog(getString(R.string.shopping_list_delete_warn)) {
            deleteUserCartItemAndUpdateUI(userCartUiData)
        }
    }

    private fun deleteUserCartItemAndUpdateUI(userCartUiData: UserCartUiData) {
        viewModel.deleteUserCartItem(userCartUiData)
        updateAdapterAfterDeletion(userCartUiData)
    }

    private fun updateAdapterAfterDeletion(userCartUiData: UserCartUiData) {
        val newList = adapter.currentList.filter { it.productId != userCartUiData.productId }
        adapter.submitList(newList)
        updateTotalPriceAndUI(newList)
        if (newList.isEmpty()) {
            hideCartBadge()
        }
    }

    private fun updateTotalPriceAndUI(cartList: List<UserCartUiData>) {
        val totalPrice = viewModel.calculateTotalPrice(cartList)
        viewModel.updateTotalPrice(totalPrice)
        binding.tvTotalAmount.text = totalPrice.toString()
        requireView().showToast(getString(R.string.shopping_list_item_deleted_txt))
    }

    private fun hideCartBadge() {
        viewModel.setBadgeState(
            UserCartBadgeEntity(
                getUserIdFromSharedPref(sharedPref),
                false
            )
        )
        showBadgeVisibility(false)
    }

    private fun onItemShortClicked(userCartUiData: UserCartUiData) {
        val action =
            CartFragmentDirections.actionCartFragmentToDetailFragment(userCartUiData.productId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
