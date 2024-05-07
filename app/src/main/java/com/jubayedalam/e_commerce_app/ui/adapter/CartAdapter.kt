package com.jubayedalam.e_commerce_app.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jubayedalam.e_commerce_app.databinding.EachCartForCartSectionBinding
import com.jubayedalam.e_commerce_app.room.CartDB
import com.jubayedalam.e_commerce_app.room.CartEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartAdapter(private val cartDB: CartDB) : ListAdapter<CartEntity, CartAdapter.CartViewHolder>(DiffCallback()) {

    private lateinit var onDeleteClickListener: (CartEntity) -> Unit

    fun setOnDeleteClickListener(listener: (CartEntity) -> Unit) {
        onDeleteClickListener = listener
    }

    interface OnPlusClickListener {
        fun onPlusClick(product: CartEntity)
    }
    interface OnMinusClickListener {
        fun onMinusClick(product: CartEntity)
    }

    var onPlusClickListener: OnPlusClickListener? = null
    var onMinusClickListener: OnMinusClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = EachCartForCartSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        holder.binding.deletedIcon.setOnClickListener {
            val item = getItem(holder.adapterPosition)
            onDeleteClickListener.invoke(item)
        }

        holder.binding.imagePlus.setOnClickListener {
            Log.d("CatAdapter", "CartProductIncress")
            // Increase the quantity
            product.quantity++
            CoroutineScope(Dispatchers.IO).launch {
                updateCart(product)
            }
            // Update the displayed quantity
            holder.binding.tvCartProductQuantity.text = product.quantity.toString()
        }
        holder.binding.imageMinus.setOnClickListener {
            Log.d("CatAdapter", "CartProductDecress")
            // Increase the quantity
            product.quantity--
            CoroutineScope(Dispatchers.IO).launch {
                updateCart(product)
            }
            // Update the displayed quantity
            if (product.quantity < 1){
                val updatedList = currentList.toMutableList()
                updatedList.removeAt(position)
                submitList(updatedList)
            }else{
                holder.binding.tvCartProductQuantity.text = product.quantity.toString()
            }

        }



    }


    private suspend fun updateCart(product: CartEntity) {
        cartDB.productDao().updateProduct(product)
    }

    inner class CartViewHolder(val binding: EachCartForCartSectionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: CartEntity) {
            binding.apply {
                // Bind product data to UI elements
                tvProductCartName.text = product.productName
                tvProductCartPrice.text = "৳ ${product.productPrice}"
                tvCartProductQuantity.text = product.quantity.toString()
                Glide.with(itemView.context)
                    .load(product.imageUrl)
                    .into(imageCartProduct)

             // Set onClickListener for plus icon
                /*imagePlus.setOnClickListener {
                    Log.d("CatAdapter", "CartProductIncress")
                    // Increase the quantity
                    product.quantity++
                    // Update the displayed quantity
                    tvCartProductQuantity.text = product.quantity.toString()
                }*/

                /*
                             // Set onClickListener for minus icon
                             imageMinus.setOnClickListener {
                                 // Decrease the quantity if greater than 0
                                 if (product.quantity > 0) {
                                     product.quantity--
                                     // Update the displayed quantity
                                     tvCartProductQuantity.text = product.quantity.toString()
                                     // Remove the item if quantity becomes 0
                                     if (product.quantity == 0) {
                                         val position = adapterPosition
                                         if (position != RecyclerView.NO_POSITION) {
                                             val updatedList = currentList.toMutableList()
                                             updatedList.removeAt(position)
                                             submitList(updatedList)
                                         }
                                     }
                                 }
                             }*/

                // Set onClickListener for delete icon
                /*deletedIcon.setOnClickListener {
                    // Retrieve the current position
                    val position = adapterPosition
                    // Check if the position is valid
                    if (position != RecyclerView.NO_POSITION) {
                        // Remove the item from the list
                        val updatedList = currentList.toMutableList()
                        updatedList.removeAt(position)
                        // Submit the updated list to the adapter
                        submitList(updatedList)
                    }
                }*/

                imagePlus.setOnClickListener {
                    onPlusClickListener?.onPlusClick(product)
                }
                imageMinus.setOnClickListener {
                    onMinusClickListener?.onMinusClick(product)
                }


            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CartEntity>() {
        override fun areItemsTheSame(oldItem: CartEntity, newItem: CartEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartEntity, newItem: CartEntity): Boolean {
            return oldItem == newItem
        }
    }
}
