package com.mustafaunlu.ecommerce.ui.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mustafaunlu.ecommerce.databinding.CartItemBinding
import com.mustafaunlu.ecommerce.utils.loadImage

class CartListAdapter(
    private val onItemLongClicked: (UserCartUiData) -> Unit,
    private val updateTotalPrice: () -> Unit,
    private val updateCartItem: (UserCartUiData) -> Unit,
    private val onItemShortClicked: (UserCartUiData) -> Unit,
) : ListAdapter<UserCartUiData, CartListAdapter.ShoppingListViewHolder>(
    ShoppingListDiffCallback(),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ShoppingListViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(cart: UserCartUiData) {
            binding.apply {
                cartItemTitle.text = cart.title
                cartItemPrice.text = "${cart.price} TL"
                cartItemId.text = "Product Id: ${cart.productId}"
                cartItemQuantity.text = cart.quantity.toString()
                cartItemImgView.loadImage(cart.imageUrl)
                updateTotalPrice()
            }
            binding.cartItemBtnInc.setOnClickListener {
                val updatedCart = cart.copy(quantity = cart.quantity + 1)
                updateCartItem(updatedCart)
                submitUpdatedCart(updatedCart)
                updateTotalPrice()
            }
            binding.cartItemBtnDec.setOnClickListener {
                if (cart.quantity > 1) {
                    val updatedCart = cart.copy(quantity = cart.quantity - 1)
                    updateCartItem(updatedCart)
                    submitUpdatedCart(updatedCart)
                    updateTotalPrice()
                }
            }

            binding.root.setOnClickListener {
                onItemShortClicked(cart)
            }
            binding.root.setOnLongClickListener {
                onItemLongClicked(cart)
                true
            }
        }

        private fun submitUpdatedCart(updatedCart: UserCartUiData) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val newList = currentList.toMutableList()
                newList[position] = updatedCart
                submitList(newList)
            }
        }
    }

    private class ShoppingListDiffCallback : DiffUtil.ItemCallback<UserCartUiData>() {
        override fun areItemsTheSame(oldItem: UserCartUiData, newItem: UserCartUiData): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: UserCartUiData, newItem: UserCartUiData): Boolean {
            return oldItem == newItem
        }
    }
}
