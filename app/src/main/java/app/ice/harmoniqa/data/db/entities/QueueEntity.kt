package app.ice.harmoniqa.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.ice.harmoniqa.data.model.browse.album.Track

@Entity(tableName = "queue")
data class QueueEntity(
    @PrimaryKey(autoGenerate = false)
    val queueId: Long = 0,
    val listTrack: List<Track>
)