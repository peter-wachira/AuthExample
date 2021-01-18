package com.droid.authexample.data

import android.content.SharedPreferences


class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {


    /**
     * Saves the given [SharedPreferenceEntry] that contains the user's settings to
     * [SharedPreferences].
     *
     * @param sharedPreferenceEntry contains data to save to [SharedPreferences].
     * @return `true` if writing to [SharedPreferences] succeeded, `false` otherwise.
     */
    fun saveToken(token: String): Boolean {
        // Start a SharedPreferences transaction.
        val editor = sharedPreferences.edit().apply() {
            putString(KEY_NAME, token)
        }

        // Commit changes to SharedPreferences.
        return editor.commit()
    }

    fun getToken(): String {
        val token = sharedPreferences.getString(KEY_NAME, "")
        if (!token.isNullOrEmpty()) {
            return token
        } else {
            return ""
        }
    }

    companion object {
        // Keys for saving values in SharedPreferences.
        internal val KEY_NAME = "key_name"
    }
}