package com.example.mertkesgin.mvi_example.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mertkesgin.mvi_example.R
import com.example.mertkesgin.mvi_example.data.model.Location
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character.view.*
import kotlinx.android.synthetic.main.item_location.view.*

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    val differCallback = object : DiffUtil.ItemCallback<Location>(){
        override fun areItemsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Location, newItem: Location): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_location,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = differ.currentList[position]
        holder.itemView.apply {
            tvLocationName.text = location.name
            tvType.text = location.type
            tvDimension.text = location.dimension
        }
    }
}