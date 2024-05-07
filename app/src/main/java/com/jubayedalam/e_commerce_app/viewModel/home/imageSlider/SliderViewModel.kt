package com.jubayedalam.e_commerce_app.viewModel.home.imageSlider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jubayedalam.e_commerce_app.data.home.imageSlider.ImageSlider
import com.jubayedalam.e_commerce_app.network.home.imageSlider.ImageSliderRetrofitInstance
import kotlinx.coroutines.launch

class SliderViewModel : ViewModel() {
    private val _thumbnailUrls = MutableLiveData<List<String>>()
    val thumbnailUrls: LiveData<List<String>> = _thumbnailUrls

    init {
        fetchThumbnailUrls()
    }

    private fun fetchThumbnailUrls() {
        viewModelScope.launch {
            try {
                val response = ImageSliderRetrofitInstance.apiService.getThumbnailUrls()
                val thumbnailUrlsList = extractThumbnailUrls(response)
                _thumbnailUrls.value = thumbnailUrlsList
                Log.d("ThumbnailViewModel", "Thumbnail URLs: $thumbnailUrlsList")
            } catch (e: Exception) {
                Log.e("ThumbnailViewModel", "Error: ${e.message}", e)
            }
        }
    }

    private fun extractThumbnailUrls(imageSlider: ImageSlider): List<String> {
        val thumbnailUrlsList = mutableListOf<String>()
        imageSlider.data.results.forEach { result ->
            result.slider_details.forEach { sliderDetail ->
                thumbnailUrlsList.add(sliderDetail.thumb_url)
            }
        }
        return thumbnailUrlsList
    }
}
