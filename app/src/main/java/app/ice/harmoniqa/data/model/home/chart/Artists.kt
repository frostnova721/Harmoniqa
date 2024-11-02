package app.ice.harmoniqa.data.model.home.chart


import com.google.gson.annotations.SerializedName

data class Artists(
    @SerializedName("items")
    val itemArtists: ArrayList<ItemArtist>,
    @SerializedName("playlist")
    val playlist: Any
)