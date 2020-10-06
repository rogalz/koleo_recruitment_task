package ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import sh.surge.jakub.koleorecruitmenttask.data.station.Station

fun AutoCompleteTextView.observeText(onTextChange: (text: CharSequence) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!s.isNullOrEmpty()) onTextChange(s)
        }

        override fun afterTextChanged(s: Editable?) {
        }

    })
}

fun AutoCompleteTextView.setOnclickListenerAndGetItem(): LiveData<Station?> {
    val stationLiveData = MutableLiveData<Station>()

    onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
        val tempStation = (parent?.getItemAtPosition(position) as Station)
        setText(tempStation.name)
        stationLiveData.postValue(tempStation)
    }
    return stationLiveData
}