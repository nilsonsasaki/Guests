package com.nilsonsasaki.guests.ui.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nilsonsasaki.guests.databinding.FragmentAllGuestsBinding
import com.nilsonsasaki.guests.service.constants.GuestConstants
import com.nilsonsasaki.guests.ui.viewmodels.AllGuestsViewModel
import com.nilsonsasaki.guests.ui.views.adapters.GuestListAdapter

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel
    private var _binding: FragmentAllGuestsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        allGuestsViewModel =
            ViewModelProvider(this)[AllGuestsViewModel::class.java]

        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)
        binding.rvAllGuests.layoutManager = LinearLayoutManager(context)
        allGuestsViewModel.getAllGuests()

        setObservers()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        allGuestsViewModel.getAllGuests()
    }

    private fun setObservers() {
        allGuestsViewModel.allGuestsList.observe(viewLifecycleOwner) { newList ->
            binding.rvAllGuests.adapter = GuestListAdapter(list = newList, onItemClick = {

                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUEST_ID,it.id)
                intent.putExtras(bundle)

                startActivity(intent)
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}