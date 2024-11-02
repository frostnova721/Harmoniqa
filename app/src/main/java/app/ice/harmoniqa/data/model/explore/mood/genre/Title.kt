package app.ice.harmoniqa.data.model.explore.mood.genre


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String
)