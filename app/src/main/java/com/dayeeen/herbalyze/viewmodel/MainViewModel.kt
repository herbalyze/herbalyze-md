package com.dayeeen.herbalyze.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayeeen.herbalyze.data.repository.UserRepository
import com.dayeeen.herbalyze.data.response.PlantResponseItem
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    private val _plants = MutableLiveData<List<PlantResponseItem>>()
    val plants: LiveData<List<PlantResponseItem>> get() = _plants

    init {
        getPlants()
    }

    private fun getPlants() {
        viewModelScope.launch {
            try {
                val plants = repository.getPlants()
                _plants.postValue(plants)
            } catch (e: Exception) {
                // Log exception details
                Log.e("MainViewModel", "Error fetching plants", e)
            }
        }
    }
}
