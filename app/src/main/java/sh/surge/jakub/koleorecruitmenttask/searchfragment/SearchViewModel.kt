package sh.surge.jakub.koleorecruitmenttask.searchfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ext.launchIO
import sh.surge.jakub.koleorecruitmenttask.data.station.Station
import sh.surge.jakub.koleorecruitmenttask.repo.MainRepository
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class SearchViewModel(private var repository: MainRepository) : ViewModel() {

    fun getStationWithKeyword(text: CharSequence): LiveData<List<Station>> {
        val sharedStations = MutableLiveData<List<Station>>()
        launchIO { sharedStations.postValue(repository.getStationWithKeyword(text)) }
        return sharedStations
    }

    fun getDistance(startStation: Station?, endStation: Station?): String {
        var tempString = ""
        if (startStation != null && endStation != null) {
            val tempDistance = distance(startStation.latitude, startStation.longitude, endStation.latitude, endStation.longitude)
            tempString = String.format("%.2f", tempDistance)
        }
        return "$tempString km"
    }

    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = (sin(deg2rad(lat1))
                * sin(deg2rad(lat2))
                + (cos(deg2rad(lat1))
                * cos(deg2rad(lat2))
                * cos(deg2rad(theta))))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= 60 * 1.1515
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}