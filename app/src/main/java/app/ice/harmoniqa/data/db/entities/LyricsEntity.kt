package app.ice.harmoniqa.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import app.ice.harmoniqa.data.model.metadata.Line

@Entity(tableName = "lyrics")
data class LyricsEntity (
    @PrimaryKey(autoGenerate = false) val videoId: String,
    val error: Boolean,
    val lines: List<Line>?,
    val syncType: String?
)