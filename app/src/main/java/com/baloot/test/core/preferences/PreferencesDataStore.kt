package com.baloot.test.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException

class PreferencesDataStore constructor(
    private val context: Context
) {

    private object PreferencesKeys {
        val keyIsDarkTheme = booleanPreferencesKey("key_is_dark_theme")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "baloot.test.preferences.data.store")

    val getIsDarkThemeFlow: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException)
                emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            preferences[PreferencesKeys.keyIsDarkTheme] ?: false
        }

    fun getIsDarkTheme(): Boolean = runBlocking { getIsDarkThemeFlow.first() }

    suspend fun updateIsDarkTheme(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.keyIsDarkTheme] = value
        }
    }
}
