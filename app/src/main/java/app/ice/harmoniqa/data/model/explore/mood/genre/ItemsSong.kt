package app.ice.harmoniqa.data.model.explore.mood.genre

import app.ice.harmoniqa.data.model.searchResult.songs.Artist

data class ItemsSong(
    val title: String,
    val artist: List<Artist>?,
    val videoId: String,
)
