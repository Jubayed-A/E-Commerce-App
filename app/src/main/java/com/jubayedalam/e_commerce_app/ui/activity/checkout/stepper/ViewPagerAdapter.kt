package com.jubayedalam.e_commerce_app.ui.activity.checkout.stepper

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jubayedalam.e_commerce_app.ui.activity.checkout.fragments.CheckoutPaymentFragment

class ViewPagerAdapter (fragmentManager: FragmentManager, private val fragments: List<Fragment>, val data: Any?) :
    FragmentPagerAdapter(fragmentManager) {

/*    override fun getItem(position: Int): Fragment {
        Log.d("stepper",position.toString())

//        val fragment : Fragment
//
//        when(position){
//            0 -> fragment = CheckoutInfoFragment.newInstance()
//            1 -> fragment =  CheckoutPaymentFragment.newInstance(data)
//            2 -> fragment =  CheckoutConfirmationFragment.newInstance(data)
//            else -> fragment = CheckoutInfoFragment.newInstance()
//        }

//        return fragment
        return fragments[position]
    }*/
    override fun getItem(position: Int): Fragment {
        return fragments[position].apply {
            if (this is CheckoutPaymentFragment) {
                arguments = Bundle().apply {
                    // Pass the data to the CheckoutPaymentFragment
                    putParcelable("ADDRESS_DATA", data as? Parcelable)
                }
            }
        }

    }

    override fun getCount(): Int {
        Log.d("stepper2",fragments.size.toString())
        return fragments.size
    }



}