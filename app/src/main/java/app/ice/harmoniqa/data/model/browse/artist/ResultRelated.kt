package app.ice.harmoniqa.data.model.browse.artist


import com.google.gson.annotations.SerializedName
import app.ice.harmoniqa.data.model.searchResult.songs.Thumbnail

data class ResultRelated(
    @SerializedName("browseId")
    val browseId: String,
    @SerializedName("subscribers")
    val subscribers: String,
    @SerializedName("thumbnails")
    val thumbnails: List<Thumbnail>,
    @SerializedName("title")
    val title: String
)