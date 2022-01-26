package com.nilsonsasaki.guests.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nilsonsasaki.guests.service.models.GuestModel
import com.nilsonsasaki.guests.service.repository.GuestRepository

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application.applicationContext)

    private val _allGuestList = MutableLiveData<List<GuestModel>>()
    val allGuestsList: LiveData<List<GuestModel>> = _allGuestList

    fun getAllGuests(){
        _allGuestList.value = repository.getAll()
    }

    fun deleteGuest(id: Int) {
        repository.delete(id)
        getAllGuests()
    }
}