package app.ice.harmoniqa.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.ice.harmoniqa.data.db.entities.AlbumEntity
import app.ice.harmoniqa.data.db.entities.ArtistEntity
import app.ice.harmoniqa.data.db.entities.FollowedArtistSingleAndAlbum
import app.ice.harmoniqa.data.db.entities.GoogleAccountEntity
import app.ice.harmoniqa.data.db.entities.LocalPlaylistEntity
import app.ice.harmoniqa.data.db.entities.LyricsEntity
import app.ice.harmoniqa.data.db.entities.NewFormatEntity
import app.ice.harmoniqa.data.db.entities.NotificationEntity
import app.ice.harmoniqa.data.db.entities.PairSongLocalPlaylist
import app.ice.harmoniqa.data.db.entities.PlaylistEntity
import app.ice.harmoniqa.data.db.entities.QueueEntity
import app.ice.harmoniqa.data.db.entities.SearchHistory
import app.ice.harmoniqa.data.db.entities.SetVideoIdEntity
import app.ice.harmoniqa.data.db.entities.SongEntity
import app.ice.harmoniqa.data.db.entities.SongInfoEntity

@Database(
    entities = [
        NewFormatEntity::class, SongInfoEntity::class, SearchHistory::class, SongEntity::class, ArtistEntity::class,
        AlbumEntity::class, PlaylistEntity::class, LocalPlaylistEntity::class, LyricsEntity::class, QueueEntity::class,
        SetVideoIdEntity::class, PairSongLocalPlaylist::class, GoogleAccountEntity::class, FollowedArtistSingleAndAlbum::class,
        NotificationEntity::class,
    ],
    version = 11,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3), AutoMigration(
            from = 1,
            to = 3,
        ), AutoMigration(from = 3, to = 4), AutoMigration(from = 2, to = 4), AutoMigration(
            from = 3,
            to = 5,
        ), AutoMigration(4, 5), AutoMigration(6, 7), AutoMigration(
            7,
            8,
            spec = AutoMigration7_8::class,
        ), AutoMigration(8, 9),
        AutoMigration(9, 10),
    ],
)
@TypeConverters(Converters::class)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun getDatabaseDao(): DatabaseDao
}