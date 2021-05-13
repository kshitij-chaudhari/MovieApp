package com.mintu.movieapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mintu.movieapp.R
import com.mintu.movieapp.databinding.ItemLayoutBinding
import com.mintu.presentation.model.Data

class MoviesListAdapter (
    private var data: List<Data>
) : RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Data) {
            binding.title.text = item.title
            binding.genre.text = String.format("Genre: %s", item.genre)
            binding.year.text = String.format("Year: %s", item.year)

            Glide.with(binding.root.context)
                .load(item.poster)
                .placeholder(R.drawable.poster_placeholder)
                .into(binding.poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setNewItems(records: List<Data>) {
        val oldList = this.data
        this.data = records
        val diff = DiffUtil.calculateDiff(DiffUtilCallback(this.data, oldList))
        diff.dispatchUpdatesTo(this)
    }
}