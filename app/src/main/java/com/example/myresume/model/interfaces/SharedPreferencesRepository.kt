package com.example.myresume.model.interfaces

/**
 * Shared Preferences contract for SharedPreferences that provides a repository of useful functions.
 * It is injected by Dagger.
 * @see com.example.myresume.dagger.module.SharedPreferencesModule
 */
interface SharedPreferencesRepository {
    fun putInt(key: String, value: Int)
    fun getInt(key: String): Int
    fun putString(key: String, value: String)
    fun getString(key: String): String
}
