package com.jubayedalam.e_commerce_app.ui.adapter.product.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.jubayedalam.e_commerce_app.data.home.trending.Product
import com.jubayedalam.e_commerce_app.data.product.item.ProductItem
import com.jubayedalam.e_commerce_app.databinding.ProductEachItemBinding

class ProductItemAdapter(private val productItem: ArrayList<ProductItem>) : RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder>() {

    var onItemClickListener: ((ProductItem) -> Unit)? = null

    var onAddToCartClick: ((Product) -> Unit)? = null

    class ProductItemViewHolder(val binding: ProductEachItemBinding) : ViewHolder(binding.root){}

    fun setProductItem(productItemList : ArrayList<ProductItem>){
        this.productItem.clear()
        this.productItem.addAll(productItemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        return ProductItemViewHolder(
            ProductEachItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int {
        return productItem.size
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val product = productItem[position]
        holder.binding.apply {
            productItemName.text = product.name
            productItemPrice.text = "৳ ${product.price}"
            Glide.with(holder.itemView.context)
                .load(product.thumbUrl)
                .into(productItemImg)
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(product)
        }

    }


}