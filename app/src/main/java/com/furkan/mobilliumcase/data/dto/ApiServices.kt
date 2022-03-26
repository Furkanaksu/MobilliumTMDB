package com.furkan.mobilliumcase.data.dto

import com.furkan.mobilliumcase.data.model.GenreModel
import com.furkan.mobilliumcase.data.model.MoviesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {


    @GET("movie/now_playing")
    suspend fun getMovies(
        @Query("api_key") api: String,
        @Query("language") language: String
    ): GenreModel

    @GET("movie/upcoming")
    suspend fun comingMoVies(
        @Query("api_key") api: String,
        @Query("language") language: String
    ): GenreModel

}