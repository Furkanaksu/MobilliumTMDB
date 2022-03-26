package com.furkan.mobilliumcase.data.repository

import com.furkan.mobilliumcase.data.dto.ApiServices
import com.furkan.mobilliumcase.utils.Constants.API_KEY
import com.furkan.mobilliumcase.utils.Constants.LANGUAGE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepository @Inject constructor(private val apiService: ApiServices) {

    suspend fun getMovies() = apiService.getMovies(API_KEY, LANGUAGE)

    suspend fun getNewMoviews() = apiService.comingMoVies(API_KEY, LANGUAGE)

}