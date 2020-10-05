package sh.surge.jakub.koleorecruitmenttask.activity.vm

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sh.surge.jakub.koleorecruitmenttask.repo.MainRepository
import sh.surge.jakub.koleorecruitmenttask.utils.Const
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private var repository: MainRepository,
    private var sharedPreferences: SharedPreferences
) : ViewModel() {
    val observeStationsAndKeywords = repository.stationAndKeywords

    fun updateDataIfNeeded() {
        viewModelScope.launch {
            val currentTime = System.currentTimeMillis()
            val lastTimeOfUpdate = sharedPreferences.getLong(Const.SHARED_PREFERENCES_KEY, 0)
            val nextUpdateTime = lastTimeOfUpdate + Const.MILLIS_24h

            if (nextUpdateTime < currentTime || lastTimeOfUpdate == 0L) {
                if (repository.getAndSaveData())
                    sharedPreferences.edit().putLong(Const.SHARED_PREFERENCES_KEY, currentTime).apply()
            }
        }
    }
}