package com.jubayedalam.e_commerce_app.ui.adapter.checkout.addressGet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jubayedalam.e_commerce_app.data.checkout.addressGet.Address
import com.jubayedalam.e_commerce_app.databinding.AddressEachItemBinding

class AddressAdapter(private var addresses: List<Address>) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    private var selectedAddressPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding = AddressEachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = addresses[position]
        holder.bind(address, position)

    }

    override fun getItemCount(): Int {
        return addresses.size
    }

    inner class AddressViewHolder(private val binding: AddressEachItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(address: Address, position: Int) {
            binding.apply {
                // Bind address data to views
                addressName.text = "${address.first_name} ${address.last_name}"
                addressDetails.text = address.street_address
                addressPhone.text = address.mobile

                // Set the checked state of the radio button
                radioButton.isChecked = (position == selectedAddressPosition)

                // Update the selected address position when the radio button is clicked
                radioButton.setOnClickListener {
                    selectedAddressPosition = adapterPosition
                    notifyDataSetChanged()
                }

            }
        }
    }

    fun getSelectedAddress(): Address? {
        return if (selectedAddressPosition != RecyclerView.NO_POSITION) {
            addresses[selectedAddressPosition]
        } else {
            null
        }
    }

    fun updateData(newAddresses: List<Address>, getAddressId: Int?) {
        addresses = newAddresses
        for (i in addresses){
            if (i.id == getAddressId){
                val index = addresses.indexOf(i)
                selectedAddressPosition = index
            }
        }
        notifyDataSetChanged()
    }

}
