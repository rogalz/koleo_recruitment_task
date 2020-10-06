package sh.surge.jakub.koleorecruitmenttask.repo.networking

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import sh.surge.jakub.koleorecruitmenttask.data.keyword.Keyword
import sh.surge.jakub.koleorecruitmenttask.data.station.Station

interface KoleoApiService {

    @GET("stations")
    @Headers("X-KOLEO-Version:1")
    suspend fun getStationList(): Response<List<Station>>

    @GET("station_keywords")
    @Headers("X-KOLEO-Version:1")
    suspend fun getKeywordsList(): Response<List<Keyword>>
}
