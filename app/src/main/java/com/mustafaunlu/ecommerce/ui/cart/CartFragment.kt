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
            ::updateSubmittedAdapterItemsTotalPrice,
            ::updateCartItemWhenClickedIncDecButton,
            ::onItemShortClicked
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.totalPriceLiveData.observe(viewLifecycleOwner) { totalPrice ->
            this.totalPrice = totalPrice.toString()
            binding.cartFragmentTotalPrice.text = totalPrice.toString()
        }

        viewModel.userCarts.observe(viewLifecycleOwner) { userCartState ->
            when (userCartState) {
                is ScreenState.Error -> {
                    binding.cartProgressBar?.gone()
                    requireView().showToast(userCartState.message)
                }

                ScreenState.Loading -> binding.cartProgressBar?.visible()
                is ScreenState.Success -> {
                    binding.cartProgressBar?.gone()
                    adapter.submitList(userCartState.uiData)
                    binding.cartFragmentRv?.adapter = adapter
                }
            }
        }
    }

    private fun updateSubmittedAdapterItemsTotalPrice() {
        val cartList = adapter.currentList
        val totalPrice = calculateTotalPrice(cartList)
        viewModel.updateTotalPrice(totalPrice)
    }

    private fun updateCartItemWhenClickedIncDecButton(userCartUiData: UserCartUiData) {
        viewModel.updateUserCartItem(userCartUiData)
    }

    private fun onItemLongClicked(userCartUiData: UserCartUiData) {
        this.showConfirmationDialog(getString(R.string.shopping_list_delete_warn)) {
            viewModel.deleteUserCartItem(userCartUiData)
            val newList = adapter.currentList.filter { it.productId != userCartUiData.productId }
            adapter.submitList(newList)
            totalPrice = calculateTotalPrice(newList).toString()
            binding.cartFragmentTotalPrice.text = totalPrice
            requireView().showToast(getString(R.string.shopping_list_item_deleted_txt))
            if (newList.isEmpty()) {
                viewModel.setBadgeState(
                    UserCartBadgeEntity(
                        getUserIdFromSharedPref(sharedPref),
                        false
                    )
                )
                showBadgeVisibility(false)
            }
        }
    }

    private fun onItemShortClicked(userCartUiData: UserCartUiData) {
        val action =
            CartFragmentDirections.actionCartFragmentToDetailFragment(userCartUiData.productId)
        findNavController().navigate(action)
    }

    private fun calculateTotalPrice(cartList: List<UserCartUiData>): Double {
        var totalPrice = 0.0
        for (cart in cartList) {
            totalPrice += cart.price * cart.quantity
        }
        return totalPrice
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}