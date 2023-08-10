package com.mustafaunlu.ecommerce.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mustafaunlu.ecommerce.databinding.ProductItemBinding
import com.mustafaunlu.ecommerce.utils.loadImage

class ProductAdapter(
    private val onItemClicked: (Int) -> Unit,
) : ListAdapter<ProductUiData, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: ProductUiData) {
            binding.apply {
                homeProductTitle?.text = product.title
                homeProductPrice?.text = "${product.price} TL"
                homeProductDescription?.text = product.description
                homeItemImg?.loadImage(product.imageUrl)
                homeProductRating?.rating = product.rating.toFloat()
            }
            binding.root.setOnClickListener {
                onItemClicked(product.id)
            }
        }
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<ProductUiData>() {
        override fun areItemsTheSame(oldItem: ProductUiData, newItem: ProductUiData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductUiData, newItem: ProductUiData): Boolean {
            return oldItem == newItem
        }
    }
}
