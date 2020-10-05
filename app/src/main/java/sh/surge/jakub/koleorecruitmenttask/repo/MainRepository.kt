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

    val stationAndKeywords: LiveData<Pair<List<Keyword>, List<Station>>> =
        database.keywordDao().getAllKeywords()
            .zipLiveData(database.stationDao().getAllStations())
}
