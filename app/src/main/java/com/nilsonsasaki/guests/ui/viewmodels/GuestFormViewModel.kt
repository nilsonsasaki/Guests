package com.nilsonsasaki.guests.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nilsonsasaki.guests.R
import com.nilsonsasaki.guests.service.models.GuestModel
import com.nilsonsasaki.guests.service.repository.GuestRepository

enum class Presence { PRESENT, ABSENT, NONE }

class GuestFormViewModel : ViewModel() {

    private val guestRepository = GuestRepository()

    private val _toastText = MutableLiveData<Int>()
    val toastText: LiveData<Int> = _toastText

    private val _radioButtonStatus = MutableLiveData(Presence.NONE)
    val radioButtonStatus: LiveData<Presence> = _radioButtonStatus

    fun save(name: String, presence: Presence?) {
        when (presence) {
            Presence.PRESENT -> {
                guestRepository.save(GuestModel(name, "present"))
                showToast(R.string.saved_successfully)
            }
            Presence.ABSENT -> {
                guestRepository.save(GuestModel(name, "absent"))
                showToast(R.string.saved_successfully)
            }
            else -> {
                showToast(R.string.saving_failed)
            }
        }
    }

    fun setRadioButton(presence: Presence) {
        if (presence != _radioButtonStatus.value) {
            _radioButtonStatus.value = presence
        } else {
            _radioButtonStatus.value = Presence.NONE
        }
    }

    private fun showToast(ref: Int) {
        _toastText.value = ref
    }
}