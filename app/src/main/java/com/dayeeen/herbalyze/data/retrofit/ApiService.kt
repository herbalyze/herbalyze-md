package com.dayeeen.herbalyze.data.retrofit

import com.dayeeen.herbalyze.data.response.PlantResponse
import com.dayeeen.herbalyze.data.response.PlantResponseItem
import retrofit2.http.GET

interface ApiService {
    // Get Plants
    @GET("plants")
    suspend fun getPlants(): List<PlantResponseItem>
}