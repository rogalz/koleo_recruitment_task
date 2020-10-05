package sh.surge.jakub.koleorecruitmenttask.activity.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import sh.surge.jakub.koleorecruitmenttask.repo.MainRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(var repository: MainRepository) : ViewModel() {


    val liveData = repository.stationsList

    init {
        viewModelScope.launch {
            delay(1500)
            repository.getStationList()
        }
    }
}