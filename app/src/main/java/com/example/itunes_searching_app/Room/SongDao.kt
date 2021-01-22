package com.example.itunes_searching_app.Room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.itunes_searching_app.models.Result

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(songList : List<Result>?)

    @Delete
    fun delete(song : Result?)

    @Query("SELECT * FROM song_table")
    fun getAllSong() : LiveData<List<Result>>

    @Query("DELETE FROM song_table")
    fun deleteAll()

    @Query("SELECT * FROM song_table ORDER BY trackName ASC")
    fun getAllSongASC() : LiveData<List<Result>>

    @Query("SELECT * FROM song_table ORDER BY trackName DESC")
    fun getAllSongDESC() : LiveData<List<Result>>

}