package com.example.rickyandmortyapp.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickyandmortyapp.databinding.MortyItemBinding
import com.example.rickyandmortyapp.models.Character


class MortyAdapter : ListAdapter<Character, MortyAdapter.MortyViewHolder>(diffCallback) {
    inner class MortyViewHolder(private val binding: MortyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.apply {
                image.load(character.image)
                name.text = character.name
            }
        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MortyViewHolder {
        return MortyViewHolder(
            MortyItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MortyViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }
}

val diffCallback = object : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }
}
