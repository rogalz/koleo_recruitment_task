package sh.surge.jakub.koleorecruitmenttask.repo

import androidx.lifecycle.MutableLiveData
import sh.surge.jakub.koleorecruitmenttask.data.station.Station
import sh.surge.jakub.koleorecruitmenttask.repo.database.KoleoDatabase
import sh.surge.jakub.koleorecruitmenttask.repo.networking.KoleoApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    var apiService: KoleoApiService,
    var database: KoleoDatabase
) {

    val stationsList: MutableLiveData<List<Station>> = MutableLiveData()

    suspend fun getStationList() {
        val response = apiService.getStationList()
        stationsList.postValue(response.body())
    }
}
