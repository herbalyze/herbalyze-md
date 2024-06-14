package com.dayeeen.herbalyze.data.di

import android.content.Context
import com.dayeeen.herbalyze.data.preferences.UserPreference
import com.dayeeen.herbalyze.data.preferences.dataStore
import com.dayeeen.herbalyze.data.repository.UserRepository
import com.dayeeen.herbalyze.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
//        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService, pref)
    }
}