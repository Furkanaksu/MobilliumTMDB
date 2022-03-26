package com.furkan.mobilliumcase.data.model

import com.google.gson.annotations.SerializedName

data class GenreModel(
    val dates: Date? = null,
    val page: Int? = null,
    val results: ArrayList<MoviesResult>? = null,

)

data class Date(
    val maximum: String? = null,
    val minimum: String? = null,
)
