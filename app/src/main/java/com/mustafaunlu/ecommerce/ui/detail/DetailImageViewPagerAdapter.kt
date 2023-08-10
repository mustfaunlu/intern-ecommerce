package com.mustafaunlu.ecommerce.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mustafaunlu.ecommerce.databinding.DetailImageItemBinding
import com.mustafaunlu.ecommerce.utils.loadImage

class DetailImageViewPagerAdapter(private val imageUrlList: List<String>) :
    RecyclerView.Adapter<DetailImageViewPagerAdapter.ViewPagerViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailImageViewPagerAdapter.ViewPagerViewHolder {
        val binding =
            DetailImageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: DetailImageViewPagerAdapter.ViewPagerViewHolder,
        position: Int
    ) {
        holder.bind(imageUrlList[position])
    }

    override fun getItemCount() = imageUrlList.size

    inner class ViewPagerViewHolder(val binding: DetailImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            binding.detailItemImg.loadImage(imageUrl)
        }
    }

}