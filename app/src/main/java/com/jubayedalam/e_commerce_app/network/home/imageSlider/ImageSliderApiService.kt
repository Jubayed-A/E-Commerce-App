package com.jubayedalam.e_commerce_app.network.home.imageSlider

import com.jubayedalam.e_commerce_app.data.home.imageSlider.ImageSlider
import retrofit2.http.GET

interface ImageSliderApiService {
    @GET("api/v1/utility/web/page/")
    suspend fun getThumbnailUrls(): ImageSlider
}