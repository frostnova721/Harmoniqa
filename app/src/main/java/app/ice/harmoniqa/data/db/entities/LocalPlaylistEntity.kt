package app.ice.harmoniqa.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import app.ice.harmoniqa.common.DownloadState
import java.time.LocalDateTime

@Entity(tableName = "local_playlist")
@Suppress("ktlint:standard:property-naming")
data class LocalPlaylistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val thumbnail: String? = null,
    val inLibrary: LocalDateTime = LocalDateTime.now(),
    val downloadState: Int = DownloadState.STATE_NOT_DOWNLOADED,
    @ColumnInfo(name = "synced_with_youtube_playlist", defaultValue = "0")
    val syncedWithYouTubePlaylist: Int = 0,
    val youtubePlaylistId: String? = null,
    @ColumnInfo(name = "youtube_sync_state", defaultValue = "0")
    val syncState: Int = YouTubeSyncState.NotSynced,
    val tracks: List<String>? = null,
    // Only synced with YouTube playlist
    // val listSetVideoId: List<String>? = null,
) {
    object YouTubeSyncState {
        const val NotSynced = 0
        const val Syncing = 1
        const val Synced = 2
    }
}