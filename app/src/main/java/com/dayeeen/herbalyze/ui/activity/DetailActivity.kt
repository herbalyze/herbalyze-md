package com.dayeeen.herbalyze.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dayeeen.herbalyze.data.response.DetailResponse
import com.dayeeen.herbalyze.databinding.ActivityDetailBinding
import com.dayeeen.herbalyze.viewmodel.DetailViewModel
import com.dayeeen.herbalyze.viewmodel.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val dvm by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plantId = intent.getStringExtra("PLANT_ID")
        if (plantId != null) {
            dvm.getPlantDetail(plantId)
        }
        dvm.plantDetail.observe(this) { plant ->
            setPlantDetails(plant)
        }
    }

    private fun setPlantDetails(plant: DetailResponse) {
        binding.apply {
            dtPlantName.text = plant.name
            dtDescription.text = plant.description
            dtUsage.text = "Cara Penggunaan:\n${plant.usage}"
            Glide.with(this@DetailActivity).load(plant.imageUrl).into(dtPlantImage)
        }
    }

}