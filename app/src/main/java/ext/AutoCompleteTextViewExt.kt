package ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView

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