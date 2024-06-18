package com.dayeeen.herbalyze.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dayeeen.herbalyze.R

class BenefitsAdapter(private val benefits: List<String>) : RecyclerView.Adapter<BenefitsAdapter.BenefitViewHolder>() {

    class BenefitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val benefitText: TextView = view.findViewById(R.id.benefitText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenefitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_benefit, parent, false)
        return BenefitViewHolder(view)
    }

    override fun onBindViewHolder(holder: BenefitViewHolder, position: Int) {
        holder.benefitText.text = "- ${benefits[position]}"
    }

    override fun getItemCount(): Int {
        return benefits.size
    }
}