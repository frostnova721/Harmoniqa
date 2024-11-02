package app.ice.harmoniqa.data.model.home


import com.google.gson.annotations.SerializedName
import app.ice.harmoniqa.data.model.searchResult.songs.Thumbnail

data class HomeItem(
    @SerializedName("contents")
    val contents: List<Content?>,
    @SerializedName("title")
    val title: String,
    val subtitle: String? = null,
    val thumbnail: List<Thumbnail>? = null,
    val channelId: String? = null,
)