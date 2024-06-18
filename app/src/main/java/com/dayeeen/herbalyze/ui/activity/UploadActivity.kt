package com.dayeeen.herbalyze.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dayeeen.herbalyze.databinding.ActivityUploadBinding
import com.dayeeen.herbalyze.domain.model.PredictResult
import com.dayeeen.herbalyze.utils.StateResult
import com.dayeeen.herbalyze.utils.getImageUri
import com.dayeeen.herbalyze.utils.reduceFileImage
import com.dayeeen.herbalyze.utils.uriToFile
import com.dayeeen.herbalyze.viewmodel.UploadViewModel
import com.dayeeen.herbalyze.viewmodel.ViewModelFactory
import java.text.DecimalFormat

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private var currentImageUri: Uri? = null
    private val uploadViewModel by viewModels<UploadViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.galleryButton.setOnClickListener { startGallery() }
        binding.cameraButton.setOnClickListener { startCamera() }
        binding.uploadButton.setOnClickListener { uploadStory() }

    }

    private fun startCamera() {
        currentImageUri = getImageUri(this)
        currentImageUri?.let { uri ->
            launcherIntentCamera.launch(uri)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.thumbnail.setImageURI(it)
        }
    }

    private fun uploadStory() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, this).reduceFileImage()
            uploadViewModel.uploadImage(imageFile).observe(this) { stateResult ->
                when (stateResult) {
                    is StateResult.Failure -> {
                        binding.apply {
                            llLoading.visibility = View.GONE
                            uploadButton.visibility = View.VISIBLE
                        }
                        Toast.makeText(this, stateResult.message, Toast.LENGTH_SHORT).show()
                    }
                    StateResult.InProgress -> {
                        binding.apply {
                            llLoading.visibility = View.VISIBLE
                            uploadButton.visibility = View.GONE
                        }
                    }
                    is StateResult.Success -> {
                        binding.apply {
                            llLoading.visibility = View.GONE
                            uploadButton.visibility = View.VISIBLE
                        }
                        val predictResult = PredictResult(
                            id = stateResult.data.id ?: "",
                            name = stateResult.data.name ?: "",
                            imageUrl = stateResult.data.imageUrl ?: "",
                            description = stateResult.data.description ?: "",
                            scorePredict = stateResult.data.predictionScore ?: 0.0
                        )
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra("PREDICT_RESULT", predictResult)
                        startActivity(intent)
                    }
                }
            }
            Log.d("Image File", "showImage: ${imageFile.path}")
        }
    }
}