package sh.surge.jakub.koleorecruitmenttask.di

import android.content.Context
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import sh.surge.jakub.koleorecruitmenttask.activity.vm.MainViewModel
import sh.surge.jakub.koleorecruitmenttask.repo.MainRepository
import sh.surge.jakub.koleorecruitmenttask.repo.database.KoleoDatabase
import sh.surge.jakub.koleorecruitmenttask.repo.networking.KoleoApiService
import sh.surge.jakub.koleorecruitmenttask.repo.networking.RetrofitInstance
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
    fun provideViewModel(repository: MainRepository) = MainViewModel(repository)
}