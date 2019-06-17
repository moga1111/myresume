package com.example.myresume.model

import android.content.Context
import android.content.SharedPreferences
import com.example.myresume.model.interfaces.SharedPreferencesRepository
import com.example.myresume.util.Constants

/**
 * Custom Shared Preferences Repository Implementation
 */
class SharedPreferencesRepositoryImplementation(context: Context) : SharedPreferencesRepository {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE
    )

    /**
     * Store integer in the xml file
     * @param key Key
     * @param value Value
     */
    override fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    /**
     * Get integer of a given key in the xml file
     * @param key Key
     * @return Integer value or 0 if not found
     */
    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    /**
     * Get string of a given key in the xml file
     * @param key Key
     * @return String value or empty string if not found
     */
    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    /**
     * Stores a new value in xml file
     * @param key Key
     * @param value Value
     */
    override fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }
}
