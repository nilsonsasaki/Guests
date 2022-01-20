package com.nilsonsasaki.guests.service.repository

import android.content.ContentValues
import android.content.Context
import com.nilsonsasaki.guests.service.constants.DatabaseConstants
import com.nilsonsasaki.guests.service.models.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {

    private var guestDatabaseHelper = GuestDatabaseHelper(context)

    companion object {
        private lateinit var repository: GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return list
    }

    fun save(guest: GuestModel): Boolean {
        return try {

            val db = guestDatabaseHelper.writableDatabase
            val contentValues = ContentValues()

            contentValues.put(DatabaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

            db.insert(DatabaseConstants.GUEST.TABLE_NAME, null, contentValues)

            true
        } catch (e: Exception) {
            false
        }
    }

    fun update(guest: GuestModel) {

    }

    fun delete(guest: GuestModel) {

    }
}