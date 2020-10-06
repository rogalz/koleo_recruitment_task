package sh.surge.jakub.koleorecruitmenttask.repo

import androidx.lifecycle.LiveData
import ext.zipLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sh.surge.jakub.koleorecruitmenttask.data.keyword.Keyword
import sh.surge.jakub.koleorecruitmenttask.data.station.Station
import sh.surge.jakub.koleorecruitmenttask.repo.database.KoleoDatabase
import sh.surge.jakub.koleorecruitmenttask.repo.networking.KoleoApiService
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
    private var apiService: KoleoApiService,
    private var database: KoleoDatabase
) {
    val keywords = database.keywordDao().getAllKeywords()
    val stations = database.stationDao().getAllStations()
    val stationAndKeywords: LiveData<Pair<List<Keyword>, List<Station>>> = keywords.zipLiveData(stations)

    suspend fun getAndSaveData(): Boolean {
        try {
            return getAndSaveStationList() && getAndSaveKeywordsList()
        } catch (e: Exception) {
            Timber.d("exception: ${e.message}")
        }
        return false
    }

    private suspend fun getAndSaveStationList(): Boolean {
        val response = apiService.getStationList()
        withContext(Dispatchers.IO) { response.body()?.let { database.stationDao().insertAll(it) } }
        return response.isSuccessful
    }

    private suspend fun getAndSaveKeywordsList(): Boolean {
        val response = apiService.getKeywordsList()
        withContext(Dispatchers.IO) { response.body()?.let { database.keywordDao().insertAll(it) } }
        return response.isSuccessful
    }

    suspend fun getStationWithKeyword(text: CharSequence): List<Station> {
        val stationsIdList = database.keywordDao().getKeywordsStartedWith(text.toString()).map { it.stationId }
        return database.stationDao().getStationsWithId(stationsIdList)
    }
}