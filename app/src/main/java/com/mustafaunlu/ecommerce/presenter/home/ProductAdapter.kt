package com.mustafaunlu.ecommerce.presenter.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mustafaunlu.ecommerce.common.AllProductsUiData
import com.mustafaunlu.ecommerce.databinding.ProductItemBinding
import com.mustafaunlu.ecommerce.utils.loadImage

class ProductAdapter(
    private val onItemClicked: (Int) -> Unit,
) : ListAdapter<AllProductsUiData, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ProductViewHolder(private val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(product: AllProductsUiData) {
            binding.apply {
                productTitle.text = product.title
                productPrice.text = "${product.price} TL"
                productDescription.text = product.description
                productImg.loadImage(product.imageUrl)
            }
            binding.root.setOnClickListener {
                onItemClicked(product.id)
            }
        }
    }

    private class ProductDiffCallback : DiffUtil.ItemCallback<AllProductsUiData>() {
        override fun areItemsTheSame(oldItem: AllProductsUiData, newItem: AllProductsUiData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AllProductsUiData, newItem: AllProductsUiData): Boolean {
            return oldItem == newItem
        }
    }
}
