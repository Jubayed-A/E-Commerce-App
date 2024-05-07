package com.jubayedalam.e_commerce_app.ui.adapter.product.category

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jubayedalam.e_commerce_app.R
import com.jubayedalam.e_commerce_app.data.product.category.ProductCategoryList
import com.jubayedalam.e_commerce_app.databinding.TablayoutEachItemBinding

class ProductCategoryAdapter (private val productList: ArrayList<ProductCategoryList>) :
    RecyclerView.Adapter<ProductCategoryAdapter.ProductListViewHolder>() {

    private var selectedItemPosition = RecyclerView.NO_POSITION // Initially, no item is selected
    private lateinit var onItemClickListener : OnItemClickListener


    interface OnItemClickListener{
        fun onItemClickListener(position: Int)
    }


    class ProductListViewHolder(val binding: TablayoutEachItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(
            TablayoutEachItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val currentItem = productList[position]

        holder.binding.apply {
            productName.text = currentItem.name


            // Set background tint, text color, and image color based on selection
            if (currentItem.isSelected) {
                productItemRoot.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
                productName.setTextColor(Color.WHITE)
            } else {
                productItemRoot.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.white
                    )
                )
                productName.setTextColor(
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


    fun getUpdated(item: ArrayList<ProductCategoryList>) {
        this.productList.clear()
        this.productList.addAll(item)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

}