package com.dayeeen.herbalyze.data.response

import com.google.gson.annotations.SerializedName

data class PredictionResponse(

	@field:SerializedName("predictionScore")
	val predictionScore: Double? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("imageUrl")
	val imageUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("_id")
	val id: String? = null
)
