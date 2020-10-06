package ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
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

fun AutoCompleteTextView.setOnclickListenerAndGetItem(): Station? {
    var station: Station? = null
    onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
        station = (parent?.getItemAtPosition(position) as Station)
        setText(station?.name)
    }
    return station
}