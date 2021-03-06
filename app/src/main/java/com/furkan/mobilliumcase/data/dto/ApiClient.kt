package com.furkan.mobilliumcase.data.dto

import com.furkan.mobilliumcase.utils.Constants.BaseWebservice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiClient {

    @Singleton
    @Provides
    fun retrofit(): ApiServices = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BaseWebservice)
        .build()
        .create(ApiServices::class.java)

    @Singleton
    @Provides
    fun run(): ApiServices = retrofit()
}