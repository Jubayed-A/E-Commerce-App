package com.jubayedalam.e_commerce_app.ui.adapter.home.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jubayedalam.e_commerce_app.data.home.trending.Product
import com.jubayedalam.e_commerce_app.databinding.TrendingEachItemBinding

class TrendingHomeAdapter(private val trendingList: ArrayList<Product>) :
    RecyclerView.Adapter<TrendingHomeAdapter.TrendingHomeViewHolder>() {


    var onItemClickListener: ((Product) -> Unit)? = null

    var onAddToCartClick: ((Product) -> Unit)? = null

    // Define a method to set the item click listener
    /*    fun setOnItemClickListener(listener: (Product) -> Unit) {
            itemClickListener = listener
        }*/

    class TrendingHomeViewHolder(val binding: TrendingEachItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


    fun getUpdated(item: ArrayList<Product>) {
        this.trendingList.clear()
        this.trendingList.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingHomeViewHolder {
        return TrendingHomeViewHolder(
            TrendingEachItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }

    override fun onBindViewHolder(holder: TrendingHomeViewHolder, position: Int) {
        val currentItem = trendingList[position]

        holder.binding.apply {
            tvProductName.text = currentItem.name
            tvProductPrice.text = "৳ ${currentItem.price}"
            Glide.with(holder.itemView.context)
                .load(currentItem.thumbUrl)
                .into(trendingProductImg)


            btnShop.setOnClickListener {
                onAddToCartClick?.let { it1 -> it1(currentItem) }
            }


        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(currentItem)
        }

    }


}