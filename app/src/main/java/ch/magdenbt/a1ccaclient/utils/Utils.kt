package ch.magdenbt.a1ccaclient.utils

import android.widget.EditText

fun EditText.setTextIfDifferent(newText: CharSequence){
    if (text.toString() != newText) {
        setText(newText)
    }
}