package com.example.itunes_searching_app.Room

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room.databaseBuilder
import com.example.itunes_searching_app.models.Result

@Database(entities = [Result::class], version = 3, exportSchema = false)
public abstract class SongDatabase : RoomDatabase() {

    abstract val songDao: SongDao

    companion object {

        private var INSTANCE: SongDatabase? = null

        fun getInstance(context: Context): SongDatabase {
            synchronized(this) {

                var instance = INSTANCE

                if(instance == null) {
                    instance = databaseBuilder (
                        context.applicationContext,
                        SongDatabase::class.java,
                        "song_database"
                            ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}