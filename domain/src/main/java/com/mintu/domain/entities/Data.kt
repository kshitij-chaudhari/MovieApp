package com.mintu.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Data(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title", collate = ColumnInfo.NOCASE)
    var title: String,

    @ColumnInfo(name = "year")
    var year: String,

    @ColumnInfo(name = "genre", collate = ColumnInfo.NOCASE)
    var genre: String,

    @ColumnInfo(name = "poster")
    var poster: String
)
