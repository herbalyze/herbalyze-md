package com.dayeeen.herbalyze.data.repository

import com.dayeeen.herbalyze.data.preferences.UserPreference
import com.dayeeen.herbalyze.data.response.DetailResponse
import com.dayeeen.herbalyze.data.response.PlantResponseItem
import com.dayeeen.herbalyze.data.response.PredictionResponse
import com.dayeeen.herbalyze.data.retrofit.ApiService
import com.dayeeen.herbalyze.utils.StateResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class UserRepository private constructor(
    private var apiService: ApiService,
    private val userPreference: UserPreference
) {
    suspend fun getPlants(): List<PlantResponseItem> {
        return apiService.getPlants()
    }

    suspend fun getPlantDetail(id: String): DetailResponse {
        return apiService.getPlantDetail(id)
    }

    fun uploadImageFile(imageFile: File, userId: Int = 1): Flow<StateResult<PredictionResponse>> = flow {
        emit(StateResult.InProgress)
        try {
            val requestFile = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
            val result = apiService.uploadImage(body, userId)
            emit(StateResult.Success(result))
        } catch (e: Exception) {
            emit(StateResult.Failure(e.message ?: ""))
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(apiService: ApiService, userPreference: UserPreference) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}