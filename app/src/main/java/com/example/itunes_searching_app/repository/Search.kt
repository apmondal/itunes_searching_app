package com.example.itunes_searching_app.repository

import android.app.Application
import android.os.AsyncTask
import android.telecom.Call
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.itunes_searching_app.Room.SongDao
import com.example.itunes_searching_app.Room.SongDatabase
import com.example.itunes_searching_app.api.RetrofitService
import com.example.itunes_searching_app.models.Result
import com.example.itunes_searching_app.api.Interface
import com.example.itunes_searching_app.models.RetrofitModel
import retrofit2.Callback
import retrofit2.Response

class Search(val application: Application) {
    private val database = SongDatabase.getInstance(application)
    private val SongList: MutableLiveData<List<Result>>? = MutableLiveData<List<Result>>()

    fun searchArtist(name: String) {

        val service = RetrofitService.buildService(Interface::class.java)

        val call = service.getRetrofitModel(name).enqueue(object : Callback<RetrofitModel> {
            override fun onResponse(call: retrofit2.Call<RetrofitModel>, response: Response<RetrofitModel>) {

                if(response.isSuccessful) {

                    SongList?.value = response.body()?.results

                    setDatabase()

                    insert(SongList?.value)
                }
            }

            override fun onFailure(call: retrofit2.Call<RetrofitModel>, t: Throwable) {
            }
        })
    }

    fun insert(songList: List<Result>?) {
        InsertAsyncTask(database).execute(songList)
    }

    class InsertAsyncTask(songDatabase: SongDatabase) : AsyncTask<List<Result>?, Void?, Void>() {
        private val songDao = songDatabase.songDao
        override fun doInBackground(vararg params: List<Result>?): Void? {
            songDao.insert(params[0])
            return null
        }
    }

    fun setDatabase() {
        DeleteAllAsyncTask(database).execute()
    }

    class DeleteAllAsyncTask(songDatabase: SongDatabase) : AsyncTask<Void, Void, Void>() {
        private val songDao = songDatabase.songDao
        override fun doInBackground(vararg params: Void?): Void? {
            songDao.deleteAll()
            return null
        }
    }

    fun delete(song: Result) {
        DeleteAsyncTask(database).execute()
    }

    class DeleteAsyncTask(songDatabase: SongDatabase) : AsyncTask<Result, Void, Void>() {
        private val songDao = songDatabase.songDao
        override fun doInBackground(vararg params: Result?): Void? {
            songDao.delete(params[0])
            return null
        }
    }

    fun getAllSong(): LiveData<List<Result>>? {
        return database.songDao.getAllSong()
    }

    fun getAllSongAscending(): LiveData<List<Result>>? {
        return database.songDao.getAllSongASC()
    }

    fun getAllSongDescending(): LiveData<List<Result>>? {
        return database.songDao.getAllSongDESC()
    }
}