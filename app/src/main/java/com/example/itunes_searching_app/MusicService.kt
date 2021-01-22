package com.example.itunes_searching_app

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicService : Service(){
    private var URL: String? = null
    private var TEMP: String? = null

    companion object {
        private var INSTANCE: MediaPlayer? = null

        fun getInstance(): MediaPlayer {
            var instance = INSTANCE

            if(instance == null) {
                instance = MediaPlayer()
                INSTANCE = instance
            }

            return instance
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val musicPlayer = getInstance()

        if (intent != null) {
            Log.d("music","called")
            TEMP = intent.getStringExtra("URL")
        }

        if(TEMP == URL) {
            if(musicPlayer.isPlaying)
                musicPlayer.pause()
            else if(musicPlayer.duration == 0) {
                musicPlayer.reset()

                URL = TEMP
                Log.d("music","called")
                musicPlayer.setDataSource(URL)

                musicPlayer.prepare()
                musicPlayer.start()
            }
            else
                musicPlayer.start()
        }
        else {
            if(musicPlayer.isPlaying)
                musicPlayer.stop()

            musicPlayer.reset()

            URL = TEMP
            musicPlayer.setDataSource(URL)

            musicPlayer.prepare()
            musicPlayer.start()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        URL = null

        INSTANCE?.stop()
        INSTANCE?.reset()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}