package com.mustafaunlu.ecommerce.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.recyclerview.widget.RecyclerView
import com.mustafaunlu.ecommerce.R
import com.mustafaunlu.ecommerce.databinding.CategoryItemBinding

class CategoryAdapter(
    private var categories: List<String>,
    private val onItemClicked: (String) -> Unit,
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    var selectedPosition = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("NotifyDataSetChanged")
        fun bind(category: String) {
            binding.apply {
                categoryItemName.text = category.capitalize(Locale.current)

                if (adapterPosition == selectedPosition) {
                    categoryItemName.setBackgroundResource(R.color.md_theme_light_primary)
                } else {
                    categoryItemName.setBackgroundColor(Color.TRANSPARENT)
                }

                categoryItemName.setOnClickListener {
                    onItemClicked(category)
                    selectedPosition = adapterPosition
                    notifyDataSetChanged()
                }
            }
        }
    }
}
