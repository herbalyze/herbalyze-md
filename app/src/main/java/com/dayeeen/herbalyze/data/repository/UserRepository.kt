package com.dayeeen.herbalyze.data.repository

import com.dayeeen.herbalyze.data.preferences.UserPreference
import com.dayeeen.herbalyze.data.response.DetailResponse
import com.dayeeen.herbalyze.data.response.PlantResponseItem
import com.dayeeen.herbalyze.data.retrofit.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class UserRepository private constructor(
    private var apiService: ApiService, private val userPreference: UserPreference
) {
    suspend fun getPlants(): List<PlantResponseItem> {
        return apiService.getPlants()
    }

    suspend fun getPlantDetail(id: String): DetailResponse {
        return apiService.getPlantDetail(id)
    }

    suspend fun uploadImage(imageFile: File, userId: String): DetailResponse {
        val requestFile = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
        val userIdRequestBody = userId.toRequestBody("text/plain".toMediaTypeOrNull())
        return apiService.uploadImage(body, userIdRequestBody)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(apiService: ApiService, userPreference: UserPreference) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}