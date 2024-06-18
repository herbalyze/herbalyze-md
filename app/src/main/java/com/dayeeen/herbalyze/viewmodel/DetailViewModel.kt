package com.dayeeen.herbalyze.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dayeeen.herbalyze.data.repository.UserRepository
import com.dayeeen.herbalyze.data.response.DetailResponse
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: UserRepository) : ViewModel() {

    private val _plantDetail = MutableLiveData<DetailResponse>()
    val plantDetail: LiveData<DetailResponse> get() = _plantDetail

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getPlantDetail(id: String) {
        viewModelScope.launch {
            try {
                val detail = repository.getPlantDetail(id)
                _plantDetail.postValue(detail)
            } catch (e: Exception) {
                Log.e("DetailViewModel", "Error fetching plant detail", e)
                _error.postValue(e.message)
            }
        }
    }
}
