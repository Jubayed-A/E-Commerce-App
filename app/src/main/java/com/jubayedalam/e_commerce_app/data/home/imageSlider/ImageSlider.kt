package com.jubayedalam.e_commerce_app.data.home.imageSlider

data class ImageSlider(
    val status: String,
    val code: Int,
    val data: Data,
    val message: String?
)

data class Data(
    val count: Int,
    val next: Any?,
    val previous: Any?,
    val results: List<Result>
)

data class Result(
    val slider_details: List<SliderDetail>
)

data class SliderDetail(
    val thumb_url: String
)

