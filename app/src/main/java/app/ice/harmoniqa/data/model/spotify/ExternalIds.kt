package app.ice.harmoniqa.data.model.spotify


import com.google.gson.annotations.SerializedName

data class ExternalIds(
    @SerializedName("isrc")
    val isrc: String?
)