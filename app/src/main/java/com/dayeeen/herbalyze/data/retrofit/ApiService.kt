package com.dayeeen.herbalyze.data.retrofit

import com.dayeeen.herbalyze.data.response.DetailResponse
import com.dayeeen.herbalyze.data.response.PlantResponse
import com.dayeeen.herbalyze.data.response.PlantResponseItem
import com.dayeeen.herbalyze.data.response.PredictionResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    // Get Plants
    @GET("plants")
    suspend fun getPlants(): List<PlantResponseItem>

    // Get Plant Detail
    @GET("plant/{id}")
    suspend fun getPlantDetail(
        @Path("id") id: String
    ): DetailResponse

    @Multipart
    @POST("predict")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("userId") userId: Int
    ): PredictionResponse
}