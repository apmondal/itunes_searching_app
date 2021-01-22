package com.example.itunes_searching_app.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "song_table")
data class Result (
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,

    @SerializedName("wrapperType")
    @ColumnInfo(name = "wrapperType")
    val wrapperType : String?,

    @SerializedName("kind")
    @ColumnInfo(name = "kind")
    val kind : String?,

    @SerializedName("artistId")
    @ColumnInfo(name = "artistId")
    val artistId : String?,

    @SerializedName("collectionId")
    @ColumnInfo(name = "collectionId")
    val collectionId : String?,

    @SerializedName("trackId")
    @ColumnInfo(name = "trackId")
    val trackId : String?,

    @SerializedName("artistName")
    @ColumnInfo(name = "artistName")
    val artistName : String?,

    @SerializedName("collectionName")
    @ColumnInfo(name = "collectionName")
    val collectionName : String?,

    @SerializedName("trackName")
    @ColumnInfo(name = "trackName")
    val trackName : String?,

    @SerializedName("collectionCensoredName")
    @ColumnInfo(name = "collectionCensoredName")
    val collectionCensoredName : String?,

    @SerializedName("trackCensoredName")
    @ColumnInfo(name = "trackCensoredName")
    val trackCensoredName : String?,

    @SerializedName("collectionArtistName")
    @ColumnInfo(name = "collectionArtistName")
    val collectionArtistName : String?,

    @SerializedName("artistViewUrl")
    @ColumnInfo(name = "artistViewUrl")
    val artistViewUrl : String?,

    @SerializedName("collectionViewUrl")
    @ColumnInfo(name = "collectionViewUrl")
    val collectionViewUrl : String?,

    @SerializedName("trackViewUrl")
    @ColumnInfo(name = "trackViewUrl")
    val trackViewUrl : String?,

    @SerializedName("previewUrl")
    @ColumnInfo(name = "previewUrl")
    val previewUrl : String?,

    @SerializedName("artworkUrl30")
    @ColumnInfo(name = "artworkUrl30")
    val artworkUrl30 : String?,

    @SerializedName("artworkUrl60")
    @ColumnInfo(name = "artworkUrl60")
    val artworkUrl60 : String?,

    @SerializedName("artworkUrl100")
    @ColumnInfo(name = "artworkUrl100")
    val artworkUrl100 : String?,

    @SerializedName("collectionPrice")
    @ColumnInfo(name = "collectionPrice")
    val collectionPrice : String?,

    @SerializedName("trackPrice")
    @ColumnInfo(name = "trackPrice")
    val trackPrice : String?,

    @SerializedName("releaseDate")
    @ColumnInfo(name = "releaseDate")
    val releaseDate : String?,

    @SerializedName("collectionExplicitness")
    @ColumnInfo(name = "collectionExplicitness")
    val collectionExplicitness : String?,

    @SerializedName("trackExplicitness")
    @ColumnInfo(name = "trackExplicitness")
    val trackExplicitness : String?,

    @SerializedName("discCount")
    @ColumnInfo(name = "discCount")
    val discCount : String?,

    @SerializedName("discNumber")
    @ColumnInfo(name = "discNumber")
    val discNumber : String?,

    @SerializedName("trackCount")
    @ColumnInfo(name = "trackCount")
    val trackCount : String?,

    @SerializedName("trackNumber")
    @ColumnInfo(name = "trackNumber")
    val trackNumber : String?,

    @SerializedName("trackTimeMillis")
    @ColumnInfo(name = "trackTimeMillis")
    val trackTimeMillis : Int?,

    @SerializedName("country")
    @ColumnInfo(name = "country")
    val country : String?,

    @SerializedName("currency")
    @ColumnInfo(name = "currency")
    val currency : String?,

    @SerializedName("primaryGenreName")
    @ColumnInfo(name = "primaryGenreName")
    val primaryGenreName : String?,

    @SerializedName("isStreamable")
    @ColumnInfo(name = "isStreamable")
    val isStreamable : String?
) {

}
