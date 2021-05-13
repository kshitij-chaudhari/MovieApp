package com.mintu.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mintu.domain.entities.Data

@Database(
    entities = [Data::class],
    version = 1
)
abstract class MovieDatabase  : RoomDatabase(){
    abstract fun getMovieDao() : MovieDao
    companion object {
        const val DATABASE_NAME: String = "moviedatabase"
        @Volatile private var instance : MovieDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}