package sh.surge.jakub.koleorecruitmenttask

import android.app.Application
import timber.log.Timber

class KoleoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement) =
                element.className + " " + element.lineNumber + " ***:"
        })
    }
}