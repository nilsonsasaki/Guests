package com.nilsonsasaki.guests.ui.absents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AbsentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Absents Fragment"
    }
    val text: LiveData<String> = _text
}