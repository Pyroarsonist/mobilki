package com.gmail.velikiydan.mobile.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.velikiydan.mobile.R
import com.gmail.velikiydan.mobile.data.entities.FlowerEntity


class FlowerListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<FlowerListAdapter.FlowerViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var flowers = emptyList<FlowerEntity>()

    inner class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idView: TextView = itemView.findViewById(R.id.idView)
        val nameView: TextView = itemView.findViewById(R.id.nameView)
        val minPriceView: TextView = itemView.findViewById(R.id.minPriceView)
        val maxPriceView: TextView = itemView.findViewById(R.id.maxPriceView)
        val colorView: View = itemView.findViewById(R.id.colorView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return FlowerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val current = flowers[position]
        holder.idView.text = current.id.toString()
        holder.nameView.text = current.name
        holder.minPriceView.text = current.minPrice.toString()
        holder.maxPriceView.text = current.maxPrice.toString()
        holder.colorView.setBackgroundColor(current.color)
    }

    internal fun setFlowers(flowers: List<FlowerEntity>) {
        this.flowers = flowers
        notifyDataSetChanged()
    }

    override fun getItemCount() = flowers.size
}