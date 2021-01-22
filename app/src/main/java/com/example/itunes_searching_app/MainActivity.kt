package com.example.itunes_searching_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunes_searching_app.adapter.SongAdapter
import com.example.itunes_searching_app.models.Result
import com.example.itunes_searching_app.viewModel.ViewModel

class MainActivity : AppCompatActivity(), SongAdapter.OnSongClickListner {

    private var songList: List<Result>? = null

    private lateinit var songAdapter: SongAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val searchView: SearchView = findViewById(R.id.searchID)
        searchView.queryHint = "Search artist"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchArtist(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.SongList?.observe(this, androidx.lifecycle.Observer {
            this.songList = it as ArrayList<Result>
            Log.d("list_size",it.size.toString())
            songAdapter.updateSongList(it)
        })

        recyclerView = findViewById(R.id.recyclerViewID)

        songAdapter = SongAdapter(this,this)

        recyclerView.adapter = songAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

    }

    private fun setupMusicService(url: String?) {
        if(url != null) {
            var intent = Intent(this, MusicService::class.java)
            intent.putExtra("URL",url)
            Log.d("music","called")
            startService(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        // val menu = layoutInflater.inflate(R.layout.activity_main,false)
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.order_by_name_asc -> {
                viewModel.getAllSavedSongASC()?.observe(this, Observer {
                    songList = it as ArrayList<Result>
                    songAdapter.updateSongList(it)
                })
                true
            }
            R.id.order_by_name_desc -> {
                viewModel.getAllSavedSongDESC()?.observe(this, Observer {
                    songList = it as ArrayList<Result>
                    songAdapter.updateSongList(it)
                })
                true
            }
            R.id.undo -> {
                viewModel.SongList?.observe(this, Observer {
                    songList = it as ArrayList<Result>
                    songAdapter.updateSongList(it)
                })
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClicked(position: Int) {
        Log.d("music","called")
        setupMusicService(songList?.get(position)?.previewUrl)
    }

}