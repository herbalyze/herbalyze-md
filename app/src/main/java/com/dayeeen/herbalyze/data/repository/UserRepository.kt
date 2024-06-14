package com.dayeeen.herbalyze.data.repository

import com.dayeeen.herbalyze.data.preferences.UserPreference
import com.dayeeen.herbalyze.data.response.PlantResponseItem
import com.dayeeen.herbalyze.data.retrofit.ApiService


class UserRepository private constructor(
    private var apiService: ApiService, private val userPreference: UserPreference
) {
    suspend fun getPlants(): List<PlantResponseItem> {
        return apiService.getPlants()
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