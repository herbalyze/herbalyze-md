package com.dayeeen.herbalyze.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PredictResult(
    val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val scorePredict: Double
): Parcelable
