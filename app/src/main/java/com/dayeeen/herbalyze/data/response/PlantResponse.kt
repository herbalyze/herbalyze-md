package com.dayeeen.herbalyze.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class PlantResponse(
	@field:SerializedName("PlantResponse")
	val plantResponse: List<PlantResponseItem> = emptyList(),
)

data class PlantResponseItem(
	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("_id")
	val id: String,
)
