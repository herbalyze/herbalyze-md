package com.dayeeen.herbalyze.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DetailResponse(

	@field:SerializedName("recipes")
	val recipes: List<RecipesItem>,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("usage")
	val usage: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("recipeName")
	val recipeName: String,

	@field:SerializedName("recipeGuide")
	val recipeGuide: String,

	@field:SerializedName("benefits")
	val benefits: List<String>
)

data class RecipesItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("_id")
	val id: String
)

@Parcelize
data class ResultResponse(
	@field:SerializedName("_id")
	val id: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("predictionScore")
	val predictionScore: Double
) : Parcelable
