package com.jubayedalam.e_commerce_app.ui.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jubayedalam.e_commerce_app.data.category.CategoryItems
import com.jubayedalam.e_commerce_app.databinding.CategoryEachItemBinding

class CategoryAdapter(private val categoryList: ArrayList<CategoryItems>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedItemPosition = RecyclerView.NO_POSITION // Initially, no item is selected
    private lateinit var onItemClickListener : OnItemClickListener


    interface OnItemClickListener{
        fun onItemClickListener(position: Int)
    }


    class CategoryViewHolder(val binding: CategoryEachItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryEachItemBinding.inflate(
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
            categoriesItemName.text = currentItem.name
            Glide.with(holder.itemView.context)
                .load(currentItem.thumb_url)
                .into(categoriesItemImg)


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


    fun getUpdated(item: ArrayList<CategoryItems>) {
        this.categoryList.clear()
        this.categoryList.addAll(item)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

}