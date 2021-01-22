package com.example.itunes_searching_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.itunes_searching_app.R
import com.example.itunes_searching_app.models.Result
import com.squareup.picasso.Picasso
import java.text.FieldPosition

class SongAdapter(private var context: Context, private var onSongClickListener: OnSongClickListner) :
    RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    private var songList: List<Result>? = null

    fun updateSongList(allSongs: List<Result>?) {
        this.songList = allSongs
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.result_cardview,parent,false)

        return ViewHolder(itemView, onSongClickListener)
    }

    override fun onBindViewHolder(holder: SongAdapter.ViewHolder, position: Int) {
        var song = songList?.get(position)

        holder.songName.text = song?.trackName
        holder.songImg.setBackgroundResource(R.drawable.rounded_corner)
        holder.artustName.text = song?.artistName
        holder.songLength.text = timeFormat(song?.trackTimeMillis)

        Picasso.get()
            .load(song?.artworkUrl100)
            .placeholder(R.drawable.ic_launcher_background)
            .resize(60,60)
            .into(holder.songImg)
    }

    class ViewHolder(itemView: View, private var OnSongListener: OnSongClickListner) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var songImg: ImageView = itemView.findViewById(R.id.songImgID)
        var songName: TextView = itemView.findViewById(R.id.songNameID)
        var artustName: TextView = itemView.findViewById(R.id.artistNameID)
        var songLength: TextView = itemView.findViewById(R.id.songLengthID)

        override fun onClick(p0: View?) {
            OnSongListener.onItemClicked(adapterPosition)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    override fun getItemCount(): Int {
        if(songList != null)    return songList!!.size
        return 0
    }

    interface OnSongClickListner {

        fun onItemClicked(position: Int)
    }


    private fun timeFormat(time: Int?) : String {
        if(time == null)
            return "0:00"
        var hour: String = (time/3600000).toString()
        var min: String = ((time/60000)%60).toString()
        var sec: String = ((time/1000)%60).toString()

        return "$hour:$min:$sec"
    }

}




