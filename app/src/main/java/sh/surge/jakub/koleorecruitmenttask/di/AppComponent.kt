package sh.surge.jakub.koleorecruitmenttask.di

import dagger.Component
import sh.surge.jakub.koleorecruitmenttask.activity.MainActivity
import sh.surge.jakub.koleorecruitmenttask.searchfragment.SearchFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(ac: MainActivity)
    fun inject(fr: SearchFragment)
}