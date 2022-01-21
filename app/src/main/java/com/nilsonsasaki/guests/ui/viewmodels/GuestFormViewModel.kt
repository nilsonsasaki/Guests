package com.nilsonsasaki.guests.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nilsonsasaki.guests.R
import com.nilsonsasaki.guests.service.models.GuestModel
import com.nilsonsasaki.guests.service.repository.GuestRepository

enum class Presence { PRESENT, ABSENT, NONE }

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val guestRepository = GuestRepository.getInstance(context = mContext)

    private val _toastText = MutableLiveData<Int>()
    val toastText: LiveData<Int> = _toastText

    private val _radioButtonStatus = MutableLiveData(Presence.NONE)
    val radioButtonStatus: LiveData<Presence> = _radioButtonStatus

    fun save(name: String, presence: Presence) {
        when (presence) {
            Presence.PRESENT -> {
                guestRepository.save(GuestModel(name = name, presence = true))
                showToast(R.string.saved_successfully)
            }
            Presence.ABSENT -> {
                guestRepository.save(GuestModel(name = name, presence = false))
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