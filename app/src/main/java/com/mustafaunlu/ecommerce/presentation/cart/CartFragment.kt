package com.mustafaunlu.ecommerce.presentation.cart

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.common.Constants.SHARED_PREF_DEF
import com.mustafaunlu.ecommerce.common.Constants.SHARED_PREF_USERID_KEY
import com.mustafaunlu.ecommerce.common.ScreenState
import com.mustafaunlu.ecommerce.common.UserCartUiData
import com.mustafaunlu.ecommerce.databinding.FragmentCartBinding
import com.mustafaunlu.ecommerce.utils.showConfirmationDialog
import com.mustafaunlu.ecommerce.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()
    lateinit var adapter: CartListAdapter

    @Inject
    lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        val userId = sharedPref.getString(SHARED_PREF_USERID_KEY, SHARED_PREF_DEF)!!
        viewModel.getCartsByUserId(userId.toInt())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userCarts.observe(viewLifecycleOwner) { userCartState ->
            when (userCartState) {
                is ScreenState.Error -> {
                    requireView().showToast(userCartState.message)
                }
                is ScreenState.Loading -> {
                }
                is ScreenState.Success -> {
                    adapter = CartListAdapter(::onItemLongClicked).apply {
                        submitList(userCartState.uiData)
                    }
                    binding.cartListview.adapter = adapter
                }
            }
        }
    }

    private fun onItemLongClicked(userCartUiData: UserCartUiData) {
        this.showConfirmationDialog(getString(R.string.shopping_list_delete_warn)) {
            viewModel.deleteUserCartItem(userCartUiData)
            adapter.submitList(adapter.currentList.filter { it.id != userCartUiData.id })
            requireView().showToast(getString(R.string.shopping_list_item_deleted_txt))
        }
    }
}
