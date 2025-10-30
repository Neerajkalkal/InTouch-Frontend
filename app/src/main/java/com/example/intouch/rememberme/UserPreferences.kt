package com.example.intouch.rememberme

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        private val REMEMBER_ME_KEY = booleanPreferencesKey("remember_me")
    }

    // Save Remember Me
    suspend fun saveRememberMe(value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[REMEMBER_ME_KEY] = value
        }
    }

    // Read Remember Me
    val rememberMe: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[REMEMBER_ME_KEY] ?: false }
}
