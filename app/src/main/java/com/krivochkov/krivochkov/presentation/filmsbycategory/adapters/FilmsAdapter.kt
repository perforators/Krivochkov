package com.krivochkov.krivochkov.presentation.filmsbycategory.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.krivochkov.krivochkov.presentation.loadImage
import com.krivochkov.krivochkov.databinding.FilmItemBinding
import com.krivochkov.krivochkov.domain.model.Film

class FilmsAdapter : RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>() {

    private val differ: AsyncListDiffer<Film> = AsyncListDiffer(this, DiffCallback())

    var onFilmClick: (FilmId: Int) -> Unit = { }
    var onLongClick: (FilmId: Int) -> Unit = { }

    private class DiffCallback : DiffUtil.ItemCallback<Film>() {

        override fun areItemsTheSame(oldItem: Film, newItem: Film) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Film, newItem: Film) = oldItem == newItem
    }

    class FilmViewHolder(
        private val binding: FilmItemBinding,
        private val onFilmClick: (FilmId: Int) -> Unit,
        private val onLongClick: (FilmId: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.filmName.text = film.name
            binding.isFavourite.isVisible = film.isFavourite
            binding.genreAndYear.text = film.year
            binding.poster.loadImage(film.posterUrl)
            binding.filmItem.setOnClickListener {
                onFilmClick(film.id)
            }
            binding.filmItem.setOnLongClickListener {
                onLongClick(film.id)
                true
            }
        }
    }

    fun submitFilms(films: List<Film>) {
        differ.submitList(films)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(FilmItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        ), onFilmClick, onLongClick)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size
}