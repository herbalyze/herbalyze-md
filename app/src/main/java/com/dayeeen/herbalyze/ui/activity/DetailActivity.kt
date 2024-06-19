package com.dayeeen.herbalyze.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dayeeen.herbalyze.data.response.DetailResponse
import com.dayeeen.herbalyze.databinding.ActivityDetailBinding
import com.dayeeen.herbalyze.ui.adapters.BenefitsAdapter
import com.dayeeen.herbalyze.viewmodel.DetailViewModel
import com.dayeeen.herbalyze.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val plantId = intent.getStringExtra("PLANT_ID")
        Log.d("DetailActivity", "Received PLANT_ID: $plantId")

        if (plantId != null) {
            showLoading(true)
            viewModel.getPlantDetail(plantId)
        }

        viewModel.plantDetail.observe(this) { plant ->
            showLoading(false)
            plant?.let {
                setPlantDetails(it)
            }
        }

        viewModel.error.observe(this) { errorMessage ->
            showLoading(false)
            Log.e("DetailActivity", "Error fetching plant detail: $errorMessage")
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun setPlantDetails(plant: DetailResponse) {
        binding.apply {
            dtPlantName.text = plant.name
            dtDescription.text = plant.description
            dtUsage.text = plant.usage
            dtRecipeName.text = "${plant.recipeName}:"
            dtRecipeGuide.text = plant.recipeGuide
            setupBenefitsRecyclerView(plant.benefits)
            Glide.with(this@DetailActivity)
                .load(plant.imageUrl)
                .into(dtPlantImage)
        }
    }

    private fun setupBenefitsRecyclerView(benefits: List<String>) {
        val adapter = BenefitsAdapter(benefits)
        binding.benefitsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@DetailActivity)
            this.adapter = adapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        Log.d("DetailActivity", "showLoading: $isLoading")
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}