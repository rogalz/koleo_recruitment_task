package sh.surge.jakub.koleorecruitmenttask.repo.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sh.surge.jakub.koleorecruitmenttask.utils.Const

object RetrofitInstance {
    val apiService: KoleoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KoleoApiService::class.java)
    }
}