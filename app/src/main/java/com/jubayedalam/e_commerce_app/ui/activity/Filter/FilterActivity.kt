package com.jubayedalam.e_commerce_app.ui.activity.Filter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.jubayedalam.e_commerce_app.R
import com.jubayedalam.e_commerce_app.databinding.ActivityFilterBinding
import com.jubayedalam.e_commerce_app.ui.activity.MainActivity
import com.mohammedalaa.seekbar.DoubleValueSeekBarView
import com.mohammedalaa.seekbar.OnDoubleValueSeekBarChangeListener

class FilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageFilter.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnApply.setOnClickListener {
            // Create an intent to navigate back to MainActivity and show SofaSetFragment
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("navigate_to_fragment", "sofaset_fragment")
            startActivity(intent)
        }

        binding.doubleRangeSeekbar.setOnRangeSeekBarViewChangeListener(object :
            OnDoubleValueSeekBarChangeListener {
            override fun onStartTrackingTouch(
                seekBar: DoubleValueSeekBarView?,
                min: Int,
                max: Int
            ) {
                //TODO("Not yet implemented")
            }

            override fun onStopTrackingTouch(seekBar: DoubleValueSeekBarView?, min: Int, max: Int) {
                //TODO("Not yet implemented")
            }

            override fun onValueChanged(
                seekBar: DoubleValueSeekBarView?,
                min: Int,
                max: Int,
                fromUser: Boolean
            ) {
                binding.tvMinPriceRange.text = "$min"
                binding.tvMaxPriceRange.text = "$max"

                if (seekBar?.id == R.id.double_range_seekbar) {
                    // Calculate the positions of TextViews based on the seek bar's range
                    val totalWidth = binding.doubleRangeSeekbar.width
                    val minValue = binding.doubleRangeSeekbar.minValue.toFloat()
                    val maxValue = binding.doubleRangeSeekbar.maxValue.toFloat()

                    // Calculate the percentage position of the minThumb and maxThumb along the seek bar
                    val thumbPercentageMin = (min - minValue) / (maxValue - minValue)
                    val thumbPercentageMax = (max - minValue) / (maxValue - minValue)

                    // Calculate the position of the minThumb and maxThumb relative to the seek bar's range
                    val minThumbPosition = thumbPercentageMin * totalWidth
                    val maxThumbPosition = thumbPercentageMax * totalWidth

                    // Update the layout parameters of TextViews
                    val minParams =
                        binding.tvMinPriceRange.layoutParams as ConstraintLayout.LayoutParams
                    val maxParams =
                        binding.tvMaxPriceRange.layoutParams as ConstraintLayout.LayoutParams

                    // Move the min text view with the min seek bar thumb
                    minParams.marginStart = minThumbPosition.toInt()

                    // Only move the max text view with the max seek bar thumb
                    maxParams.marginStart = maxThumbPosition.toInt()

                    // Apply the updated layout parameters to the text views
                    binding.tvMinPriceRange.layoutParams = minParams
                    binding.tvMaxPriceRange.layoutParams = maxParams

                    // Invalidate the layout to trigger a redraw and apply the changes
                    binding.tvMinPriceRange.requestLayout()
                    binding.tvMaxPriceRange.requestLayout()
                }

            }
        })

    }

}