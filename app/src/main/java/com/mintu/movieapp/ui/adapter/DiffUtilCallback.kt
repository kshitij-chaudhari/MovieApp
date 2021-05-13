package com.mintu.movieapp.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.mintu.presentation.model.Data

internal class DiffUtilCallback (private var newList: List<Data>, private var oldList: List<Data>): DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }
}