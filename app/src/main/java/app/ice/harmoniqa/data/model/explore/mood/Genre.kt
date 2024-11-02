package app.ice.harmoniqa.data.model.explore.mood


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("params")
    val params: String,
    @SerializedName("title")
    val title: String
)