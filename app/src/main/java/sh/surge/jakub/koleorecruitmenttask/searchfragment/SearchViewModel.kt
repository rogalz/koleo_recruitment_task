package sh.surge.jakub.koleorecruitmenttask.searchfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ext.launchIO
import sh.surge.jakub.koleorecruitmenttask.data.station.Station
import sh.surge.jakub.koleorecruitmenttask.repo.MainRepository

class SearchViewModel(private var repository: MainRepository) : ViewModel() {

    fun getStationWithKeyword(text: CharSequence): LiveData<List<Station>> {
        val sharedStations = MutableLiveData<List<Station>>()
        launchIO { sharedStations.postValue(repository.getStationWithKeyword(text)) }
        return sharedStations
    }

}