package ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <F, S> LiveData<F>.zipLiveData(liveData: LiveData<S>): LiveData<Pair<F, S>> {
    return MediatorLiveData<Pair<F, S>>().apply {
        var lastA: F? = null
        var lastB: S? = null

        fun update() {
            val localLastA = lastA
            val localLastB = lastB
            if (localLastA != null && localLastB != null)
                this.value = Pair(localLastA, localLastB)
        }

        addSource(this@zipLiveData) {
            lastA = it
            update()
        }
        addSource(liveData) {
            lastB = it
            update()
        }
    }
}