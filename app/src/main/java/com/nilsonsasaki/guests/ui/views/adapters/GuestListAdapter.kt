package com.nilsonsasaki.guests.ui.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nilsonsasaki.guests.databinding.ListItemBinding
import com.nilsonsasaki.guests.service.models.GuestModel

class GuestListAdapter(
    private val list: List<GuestModel>,
    private val onItemClick: (GuestModel) -> Unit,
    private val onItemLongClick: (GuestModel) -> Unit,

    ) : RecyclerView.Adapter<GuestListAdapter.GuestListViewHolder>() {

    class GuestListViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.tvGuestName
        val presence = binding.tvPresence
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return GuestListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GuestListViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.presence.text = if (list[position].presence) {
            "Present"
        } else {
            "Absent"
        }
        holder.itemView.setOnClickListener {
            onItemClick(list[position])
        }
        holder.itemView.setOnLongClickListener {
            onItemLongClick(list[position])
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}