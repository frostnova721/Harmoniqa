package app.ice.harmoniqa.data.model.explore.mood.moodmoments


import com.google.gson.annotations.SerializedName
import app.ice.harmoniqa.data.model.searchResult.songs.Thumbnail

data class Content(
    @SerializedName("playlistBrowseId")
    val playlistBrowseId: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("thumbnails")
    val thumbnails: List<Thumbnail>?,
    @SerializedName("title")
    val title: String
)