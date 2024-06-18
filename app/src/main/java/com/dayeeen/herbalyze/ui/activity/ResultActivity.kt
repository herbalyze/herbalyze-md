package com.dayeeen.herbalyze.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dayeeen.herbalyze.databinding.ActivityResultBinding
import com.dayeeen.herbalyze.domain.model.PredictResult

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val predictResult = intent.getParcelableExtra<PredictResult>("PREDICT_RESULT")

        binding.apply {
            Glide.with(this@ResultActivity)
                .load(predictResult?.imageUrl)
                .into(ivResult)
            tvName.text = predictResult?.name
            // Convert prediction score to percentage
            val scorePercent = (predictResult?.scorePredict ?: 0.0) * 100
            tvScore.text = "${String.format("%.2f", scorePercent)}%"
            tvDescription.text = predictResult?.description
//            btnToDetail.setOnClickListener {
//                val intent = Intent(this@ResultActivity, DetailActivity::class.java)
//                intent.putExtra("PLANT_ID", predictResult?.id) // Pass plant ID to DetailActivity
//                startActivity(intent)
//                finish()
//            }
            btnBackHome.setOnClickListener {
                startActivity(Intent(this@ResultActivity, MainActivity::class.java))
                finish()
            }
            btnUploadAgain.setOnClickListener {
                onBackPressed()
            }
        }
    }
}
