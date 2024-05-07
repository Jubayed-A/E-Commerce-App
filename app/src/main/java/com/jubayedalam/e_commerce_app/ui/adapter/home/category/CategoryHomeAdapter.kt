package com.jubayedalam.e_commerce_app.ui.adapter.home.category

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jubayedalam.e_commerce_app.R
import com.jubayedalam.e_commerce_app.data.home.category.CategoryList
import com.jubayedalam.e_commerce_app.databinding.CategoryHomeEachItemBinding

class CategoryHomeAdapter(private val categoryList: ArrayList<CategoryList>) :
    RecyclerView.Adapter<CategoryHomeAdapter.CategoryViewHolder>() {

    private var selectedItemPosition = RecyclerView.NO_POSITION // Initially, no item is selected
    private lateinit var onItemClickListener : OnItemClickListener


    interface OnItemClickListener{
        fun onItemClickListener(position: Int)
    }


    class CategoryViewHolder(val binding: CategoryHomeEachItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryHomeEachItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = categoryList[position]

        holder.binding.apply {
            categoryName.text = currentItem.name
            Glide.with(holder.itemView.context)
                .load(currentItem.thumb_url)
                .into(categoryImage)


            // Set background tint, text color, and image color based on selection
            if (currentItem.isSelected) {
                categoryItemRoot.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
                categoryName.setTextColor(Color.WHITE)
                linearLayout2.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            } else {
                categoryItemRoot.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.btnWhite
                    )
                )
                categoryName.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.textBlackSubTitle
                    )
                )
                categoryImage.clearColorFilter()
                linearLayout2.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.lin_img
                    )
                )
            }

            // Item click listener
            holder.itemView.setOnClickListener {
                val previousSelectedItem = selectedItemPosition
                selectedItemPosition = holder.adapterPosition
                notifyItemChanged(previousSelectedItem)
                notifyItemChanged(selectedItemPosition)
                onItemClickListener.onItemClickListener(position)
            }

        }

    }


    fun getUpdated(item: ArrayList<CategoryList>) {
        this.categoryList.clear()
        this.categoryList.addAll(item)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

}