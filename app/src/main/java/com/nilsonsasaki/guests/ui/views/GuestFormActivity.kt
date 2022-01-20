package com.nilsonsasaki.guests.ui.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.nilsonsasaki.guests.ui.viewmodels.GuestFormViewModel
import com.nilsonsasaki.guests.ui.viewmodels.Presence
import com.nilsonsasaki.guests.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityGuestFormBinding
    private val guestFormViewModel: GuestFormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setOnClickListeners()
        setObservers()
    }

    private fun setOnClickListeners() {

        _binding.btSave.setOnClickListener {
            val name = _binding.etUserName.text.toString()
            val presence: Presence? = guestFormViewModel.radioButtonStatus.value
            guestFormViewModel.save(name, presence)
        }

        _binding.rbAbsent.setOnClickListener {
            guestFormViewModel.setRadioButton(Presence.ABSENT)
        }

        _binding.rbPresent.setOnClickListener {
            guestFormViewModel.setRadioButton(Presence.PRESENT)
        }
    }

    private fun setObservers() {
        guestFormViewModel.toastText.observe(this, { ref ->
            if (ref != null) {
                Toast.makeText(applicationContext, getText(ref), Toast.LENGTH_SHORT).show()
            }
        })
        guestFormViewModel.radioButtonStatus.observe(this, { status ->
            when (status) {
                Presence.PRESENT -> {
                    _binding.rbPresent.isChecked = true
                    _binding.rbAbsent.isChecked = false
                }
                Presence.ABSENT -> {
                    _binding.rbPresent.isChecked = false
                    _binding.rbAbsent.isChecked = true
                }
                else -> {
                    _binding.rbPresent.isChecked = false
                    _binding.rbAbsent.isChecked = false
                }
            }
        })
    }


}