package com.mintu.data.mapper

import androidx.annotation.VisibleForTesting
import com.mintu.data.model.Data
import com.mintu.data.model.Movies
import com.mintu.domain.entities.Movies as MoviesDomain
import com.mintu.domain.entities.Data as DataDomain

internal class MoviesEntityMapper {
    fun toDomain(movies: Movies): MoviesDomain =
        MoviesDomain(
            data = toDomain(movies.data)
        )

    fun toDomain(dataList: List<Data>): List<DataDomain> =
        dataList.map { toDomain(it) }

    @VisibleForTesting
    internal fun toDomain(data: Data): DataDomain =
        DataDomain(
            id = data.id,
            title = data.title,
            poster = data.poster,
            genre = data.genre,
            year = data.year
        )

    fun toData(dataList: List<DataDomain>): List<Data> =
        dataList.map { toData(it) }

    @VisibleForTesting
    internal fun toData(data: DataDomain): Data =
        Data(
            id = data.id,
            title = data.title,
            poster = data.poster,
            genre = data.genre,
            year = data.year
        )
}