package sh.surge.jakub.koleorecruitmenttask.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import sh.surge.jakub.koleorecruitmenttask.repo.MainRepository
import sh.surge.jakub.koleorecruitmenttask.repo.database.KoleoDatabase
import sh.surge.jakub.koleorecruitmenttask.repo.networking.KoleoApiService
import sh.surge.jakub.koleorecruitmenttask.repo.networking.RetrofitInstance
import sh.surge.jakub.koleorecruitmenttask.utils.Const
import javax.inject.Singleton

@Module
class AppModule(var context: Context) {

    @Provides
    @Singleton
    fun providesDatabase() = KoleoDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideApiService() = RetrofitInstance.apiService

    @Provides
    @Singleton
    fun provideMainRepo(apiService: KoleoApiService, database: KoleoDatabase) =
        MainRepository(apiService, database)

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        context.getSharedPreferences(Const.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
}