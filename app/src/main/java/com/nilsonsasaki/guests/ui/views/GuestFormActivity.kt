package com.nilsonsasaki.guests.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.nilsonsasaki.guests.ui.viewmodels.GuestFormViewModel
import com.nilsonsasaki.guests.databinding.ActivityGuestFormBinding
import com.nilsonsasaki.guests.service.constants.GuestConstants

class GuestFormActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityGuestFormBinding
    private val guestFormViewModel: GuestFormViewModel by viewModels()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        loadData()
        setObservers()
        setOnClickListeners()
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            id = bundle.getInt(GuestConstants.GUEST_ID)
            guestFormViewModel.loadGuest(id)
        }
    }

    private fun setOnClickListeners() {

        _binding.btSave.setOnClickListener {
            val name = _binding.etUserName.text.toString()
            val presence: Boolean =
                guestFormViewModel.radioButtonStatus.value ?: GuestConstants.GUEST_IS_PRESENT
            if (id==0) {
                guestFormViewModel.save(id, name, presence)
            } else {
                guestFormViewModel.update(id, name, presence)
            }
            finish()
        }

        _binding.rbAbsent.setOnClickListener {
            guestFormViewModel.setRadioButton(GuestConstants.GUEST_IS_ABSENT)
        }

        _binding.rbPresent.setOnClickListener {
            guestFormViewModel.setRadioButton(GuestConstants.GUEST_IS_PRESENT)
        }
    }

    private fun setObservers() {

        guestFormViewModel.toastText.observe(this, { ref ->
            if (ref != null) {
                Toast.makeText(applicationContext, getText(ref), Toast.LENGTH_SHORT).show()
            }
        })

        guestFormViewModel.radioButtonStatus.observe(this, { status ->
            _binding.rbPresent.isChecked = status
            _binding.rbAbsent.isChecked = !status
        })

        guestFormViewModel.loadedGuest.observe(this, {
            _binding.etUserName.setText(it.name)
        })
    }


}