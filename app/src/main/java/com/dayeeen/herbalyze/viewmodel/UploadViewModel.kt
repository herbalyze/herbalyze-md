package com.dayeeen.herbalyze.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dayeeen.herbalyze.data.repository.UserRepository
import java.io.File

class UploadViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    fun uploadImage(imageFile: File) = userRepository.uploadImageFile(imageFile).asLiveData()
}