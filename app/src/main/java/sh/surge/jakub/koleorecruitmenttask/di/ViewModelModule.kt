package sh.surge.jakub.koleorecruitmenttask.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import sh.surge.jakub.koleorecruitmenttask.activity.vm.MainViewModel
import sh.surge.jakub.koleorecruitmenttask.repo.MainRepository
import sh.surge.jakub.koleorecruitmenttask.searchfragment.SearchViewModel
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun provideSearchViewModel(repository: MainRepository) = SearchViewModel(repository)

    @Provides
    @Singleton
    fun provideViewModel(repository: MainRepository, sharedPreferences: SharedPreferences) =
        MainViewModel(repository, sharedPreferences)

}