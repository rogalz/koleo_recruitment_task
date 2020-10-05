package sh.surge.jakub.koleorecruitmenttask.di

import dagger.Module
import dagger.Provides
import sh.surge.jakub.koleorecruitmenttask.activity.vm.MainViewModel
import sh.surge.jakub.koleorecruitmenttask.repo.MainRepository
import sh.surge.jakub.koleorecruitmenttask.repo.networking.KoleoApiService
import sh.surge.jakub.koleorecruitmenttask.repo.networking.RetrofitInstance
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideApiService() = RetrofitInstance.apiService

    @Provides
    @Singleton
    fun provideMainRepo(apiService: KoleoApiService) =
        MainRepository(apiService)

    @Provides
    @Singleton
    fun provideViewModel(repository: MainRepository) = MainViewModel(repository)
}