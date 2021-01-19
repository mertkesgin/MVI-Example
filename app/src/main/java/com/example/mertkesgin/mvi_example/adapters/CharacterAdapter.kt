package com.example.mertkesgin.mvi_example.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mertkesgin.mvi_example.R
import com.example.mertkesgin.mvi_example.data.model.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    val differCallback = object : DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_character,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = differ.currentList[position]
        holder.itemView.apply {
            Picasso.get().load(character.image).into(imgCharacter)
            tvName.text = character.name
            tvStatus.text = character.status
            tvSpecies.text = character.species
        }
    }
}