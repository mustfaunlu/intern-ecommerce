package com.mustafaunlu.ecommerce.presentation.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mustafaunlu.ecommerce.common.FavoriteUiData
import com.mustafaunlu.ecommerce.databinding.FavoriteItemBinding
import com.mustafaunlu.ecommerce.utils.loadImage

class FavoriteListAdapter(
    private val onItemLongClicked: (FavoriteUiData) -> Unit,
    private val onItemShortClicked: (FavoriteUiData) -> Unit,
) : ListAdapter<FavoriteUiData, FavoriteListAdapter.FavoriteListViewHolder>(
    FavoriteListDiffCallback(),
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FavoriteListViewHolder(private val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(cart: FavoriteUiData) {
            binding.apply {
                itemNameTextView.text = cart.title
                itemPriceTextView.text = "${cart.price} TL"
                itemId.text = "Product Id: ${cart.productId}"
                imageView.loadImage(cart.imageUrl)
            }

            binding.root.setOnClickListener {
                onItemShortClicked(cart)
            }
            binding.root.setOnLongClickListener {
                onItemLongClicked(cart)
                true
            }
        }
    }

    private class FavoriteListDiffCallback : DiffUtil.ItemCallback<FavoriteUiData>() {
        override fun areItemsTheSame(oldItem: FavoriteUiData, newItem: FavoriteUiData): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: FavoriteUiData, newItem: FavoriteUiData): Boolean {
            return oldItem == newItem
        }
    }
}
