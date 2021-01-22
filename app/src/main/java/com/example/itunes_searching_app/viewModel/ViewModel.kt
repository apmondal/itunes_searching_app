package com.example.itunes_searching_app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.itunes_searching_app.repository.Search
import com.example.itunes_searching_app.models.Result

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Search(application)
    val SongList : LiveData<List<Result>>?

    init {
        this.SongList = repository.getAllSong()
    }

    fun searchArtist(artistName : String) {
        repository.searchArtist(artistName)
    }

    fun insert(songList : List<Result>?) {
        repository.insert(songList)
    }

    fun getAllSavedSong() : LiveData<List<Result>>? {
        return repository.getAllSong()
    }

    fun deleteSong(song : Result) {
        repository.delete(song)
    }

    fun deleteAllSong() {
        repository.setDatabase()
    }

    fun getAllSavedSongASC() : LiveData<List<Result>>? {
        return repository.getAllSongAscending()
    }

    fun getAllSavedSongDESC() : LiveData<List<Result>>? {
        return repository.getAllSongDescending()
    }
}