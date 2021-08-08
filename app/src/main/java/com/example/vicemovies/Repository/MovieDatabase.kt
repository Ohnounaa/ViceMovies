package com.example.vicemovies.Repository


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vicemovies.Models.Movie

@Database(entities = [Movie:: class],
    version = 1,
    exportSchema = false)

    abstract class MovieDatabase: RoomDatabase() {
       abstract fun movieDao() : MovieDAO
        companion object {
            //we want database to be a singleton
            @Volatile
            private var INSTANCE: MovieDatabase? = null
            fun getInstance(context: Context): MovieDatabase? {
                synchronized(this) {
                    var instance = INSTANCE
                    if(instance == null) {
                        instance = Room.databaseBuilder(context.applicationContext,
                            MovieDatabase::class.java,
                            "movie_database")
                            .fallbackToDestructiveMigration().build()
                        INSTANCE = instance
                    }
                    return instance
                }
            }
        }
    }

