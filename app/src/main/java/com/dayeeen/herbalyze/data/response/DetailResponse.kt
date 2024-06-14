package com.dayeeen.herbalyze.data.response

import com.google.gson.annotations.SerializedName

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
	val description: String
)

data class RecipesItem(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("_id")
	val id: String
)
