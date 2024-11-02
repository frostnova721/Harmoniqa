package app.ice.harmoniqa.data.model.browse.artist


import com.google.gson.annotations.SerializedName
import app.ice.harmoniqa.data.model.searchResult.songs.Thumbnail

data class ResultSingle(
    @SerializedName("browseId")
    val browseId: String,
    @SerializedName("thumbnails")
    val thumbnails: List<Thumbnail>,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String
)