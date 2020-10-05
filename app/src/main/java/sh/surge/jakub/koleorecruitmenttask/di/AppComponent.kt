package sh.surge.jakub.koleorecruitmenttask.di

import dagger.Component
import sh.surge.jakub.koleorecruitmenttask.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(ac: MainActivity)
}