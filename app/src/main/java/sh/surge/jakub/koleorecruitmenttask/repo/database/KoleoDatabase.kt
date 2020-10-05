package sh.surge.jakub.koleorecruitmenttask.repo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sh.surge.jakub.koleorecruitmenttask.data.keyword.Keyword
import sh.surge.jakub.koleorecruitmenttask.data.keyword.KeywordDao
import sh.surge.jakub.koleorecruitmenttask.data.station.Station
import sh.surge.jakub.koleorecruitmenttask.data.station.StationDao

@Database(entities = [Station::class, Keyword::class], version = 1)
abstract class KoleoDatabase : RoomDatabase() {

    abstract fun stationDao(): StationDao
    abstract fun keywordDao(): KeywordDao

    companion object {
        @Volatile
        private var INSTANCE: KoleoDatabase? = null

        fun getDatabase(context: Context): KoleoDatabase {
            INSTANCE = init(context)
            return INSTANCE as KoleoDatabase
        }

        private fun init(context: Context): KoleoDatabase =
            Room.databaseBuilder(context.applicationContext, KoleoDatabase::class.java, "Database")
                .build()
    }
}