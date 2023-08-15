package ch.magdenbt.a1ccaclient.utils

import android.widget.EditText
import android.widget.SearchView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun EditText.setTextIfDifferent(newText: CharSequence){
    if (text.toString() != newText) {
        setText(newText)
    }
}

fun SearchView.getQueryTextChangeStateFlow() : StateFlow<String>{
    val searchQuery = MutableStateFlow("")

    setOnQueryTextListener(object: SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            newText?.let { searchQuery.value = it }
            return true
        }
    })
    return searchQuery
}