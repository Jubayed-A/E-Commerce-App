package com.jubayedalam.e_commerce_app.ui.adapter.search

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jubayedalam.e_commerce_app.R
import com.jubayedalam.e_commerce_app.data.product.category.ProductCategoryList
import com.jubayedalam.e_commerce_app.databinding.FilterEachItemBinding

class SearchAdapter(private val productList: ArrayList<ProductCategoryList>) :
    RecyclerView.Adapter<SearchAdapter.SearchListViewHolder>() {

    private var selectedItemPosition = RecyclerView.NO_POSITION // Initially, no item is selected
    private lateinit var onItemClickListener: OnItemClickListener


    interface OnItemClickListener {
        fun onItemClickListener(position: Int)
    }


    class SearchListViewHolder(val binding: FilterEachItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchListViewHolder {
        return SearchListViewHolder(
            FilterEachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchListViewHolder, position: Int) {
        val currentItem = productList[position]

        holder.binding.apply {
            categoryName.text = currentItem.name


            // Set background tint, text color, and image color based on selection
            if (currentItem.isSelected) {
                categoryNameRoot.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
                categoryName.setTextColor(Color.WHITE)
            } else {
                categoryNameRoot.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.white
                    )
                )
                categoryName.setTextColor(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.black
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

    override fun getItemCount(): Int {
        return productList.size
    }


    fun getUpdated(item: ArrayList<ProductCategoryList>) {
        this.productList.clear()
        this.productList.addAll(item)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: SearchAdapter.OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

}