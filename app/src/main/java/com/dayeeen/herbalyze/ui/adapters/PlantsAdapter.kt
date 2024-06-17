package com.dayeeen.herbalyze.ui.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dayeeen.herbalyze.R
import com.dayeeen.herbalyze.data.response.PlantResponseItem
import com.dayeeen.herbalyze.databinding.ListPlantBinding
import com.dayeeen.herbalyze.ui.activity.DetailActivity

class PlantsAdapter : ListAdapter<PlantResponseItem, PlantsAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val plant = getItem(position)
        holder.bind(plant)
    }

    class MyViewHolder(private val binding: ListPlantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(plant: PlantResponseItem) {
            binding.tvItemName.text = plant.name
            binding.tvDescription.text = plant.description
            Glide.with(itemView.context)
                .load(plant.imageUrl)
                .placeholder(R.drawable.load_photo)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.ivItemPhoto)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("PLANT_ID", plant.id)
                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        Pair.create(binding.ivItemPhoto, "photo"),
                        Pair.create(binding.tvItemName, "name"),
                        Pair.create(binding.tvDescription, "description")
                    )
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PlantResponseItem>() {
            override fun areItemsTheSame(oldItem: PlantResponseItem, newItem: PlantResponseItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PlantResponseItem, newItem: PlantResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}