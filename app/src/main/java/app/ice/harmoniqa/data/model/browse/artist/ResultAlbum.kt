package app.ice.harmoniqa.data.model.browse.artist


import com.google.gson.annotations.SerializedName
import app.ice.harmoniqa.data.model.searchResult.songs.Thumbnail

data class ResultAlbum(
    @SerializedName("browseId")
    val browseId: String,
    @SerializedName("isExplicit")
    val isExplicit: Boolean,
    @SerializedName("thumbnails")
    val thumbnails: List<Thumbnail>,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String
)