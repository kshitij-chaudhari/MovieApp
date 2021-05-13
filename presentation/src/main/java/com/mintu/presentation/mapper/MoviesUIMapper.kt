package com.mintu.presentation.mapper

import androidx.annotation.VisibleForTesting
import com.mintu.presentation.model.Movies as MoviesUI
import com.mintu.presentation.model.Data as DataUI
import com.mintu.domain.entities.*

internal class MoviesUIMapper {
    fun toUI(movies: Movies): MoviesUI =
        MoviesUI(
            data = toUI(movies.data)
        )

    fun toUI(dataList: List<Data>): List<DataUI> =
        dataList.map { toUI(it) }

    @VisibleForTesting
    internal fun toUI(data: Data): DataUI =
        DataUI(
            id = data.id,
            title = data.title,
            poster = data.poster,
            genre = data.genre,
            year = data.year
        )
}