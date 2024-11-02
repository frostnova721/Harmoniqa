package app.ice.harmoniqa.data.model.browse.artist

import app.ice.harmoniqa.data.model.searchResult.songs.Thumbnail

data class ResultPlaylist(
    val id: String,
    val author: String,
    val thumbnails: List<Thumbnail>,
    val title: String,
) {
}