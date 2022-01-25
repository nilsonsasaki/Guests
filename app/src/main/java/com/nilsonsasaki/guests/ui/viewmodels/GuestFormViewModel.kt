package com.nilsonsasaki.guests.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nilsonsasaki.guests.R
import com.nilsonsasaki.guests.service.constants.GuestConstants
import com.nilsonsasaki.guests.service.models.GuestModel
import com.nilsonsasaki.guests.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val guestRepository = GuestRepository.getInstance(context = mContext)

    private val _toastText = MutableLiveData<Int>()
    val toastText: LiveData<Int> = _toastText

    private val _loadedGuest = MutableLiveData<GuestModel>()
    val loadedGuest: LiveData<GuestModel> = _loadedGuest

    private val _radioButtonStatus = MutableLiveData(GuestConstants.GUEST_IS_PRESENT)
    val radioButtonStatus: LiveData<Boolean> = _radioButtonStatus

    fun save(id: Int = 0, name: String, presence: Boolean) {

        val isSuccessful = guestRepository.save(GuestModel(id, name, presence))
        if (isSuccessful) {
            showToast(R.string.saved_successfully)
        } else {
            showToast(R.string.saving_failed)
        }
    }

    fun setRadioButton(presence: Boolean) {
        _radioButtonStatus.value = presence
    }

    private fun showToast(ref: Int) {
        _toastText.value = ref
    }

    fun loadGuest(id: Int) {
        _loadedGuest.value = guestRepository.getGuest(id)
        loadedGuest.value?.let { setRadioButton(it.presence) }
    }
}