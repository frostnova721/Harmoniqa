package app.ice.harmoniqa.data.model.explore.mood.genre


import com.google.gson.annotations.SerializedName

data class ItemsPlaylist(
    @SerializedName("contents")
    val contents: List<Content>,
    @SerializedName("header")
    val header: String,
    @SerializedName("type")
    val type: String
)