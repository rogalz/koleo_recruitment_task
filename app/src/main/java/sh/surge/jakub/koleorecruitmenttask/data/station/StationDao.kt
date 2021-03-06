package sh.surge.jakub.koleorecruitmenttask.data.station

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<Station>)

    @Query("SELECT * FROM stations")
    fun getAllStations(): LiveData<List<Station>>

    @Query("SELECT * FROM stations WHERE id IN(SELECT stationId FROM keywords WHERE keyword LIKE :requestedKeyword || '%') ORDER by hits DESC")
    suspend fun getStationsWithId(requestedKeyword: String): List<Station>
}