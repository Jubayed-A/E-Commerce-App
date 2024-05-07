package com.jubayedalam.e_commerce_app.ui.adapter.product.fav

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jubayedalam.e_commerce_app.data.home.trending.Product
import com.jubayedalam.e_commerce_app.databinding.ProductEachItemBinding

class FavProductAdapter(private val favProducts: ArrayList<Product>) :
    RecyclerView.Adapter<FavProductAdapter.FavProductItemViewHolder>() {

    var onItemClickListener: ((Product) -> Unit)? = null

    class FavProductItemViewHolder(val binding: ProductEachItemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavProductItemViewHolder {
        return FavProductAdapter.FavProductItemViewHolder(
            ProductEachItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun getItemCount(): Int {
        return favProducts.size
    }

    override fun onBindViewHolder(holder: FavProductItemViewHolder, position: Int) {
        val product = favProducts[position]
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

    fun setProductItem(favProductItemList : ArrayList<Product>){
        this.favProducts.clear()
        this.favProducts.addAll(favProductItemList)
        notifyDataSetChanged()
    }


}