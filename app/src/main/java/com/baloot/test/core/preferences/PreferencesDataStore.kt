package com.baloot.test.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.baloot.test.core.db.AppDb

class PreferencesDataStore constructor(
    private val context: Context,
    private val appDb: AppDb
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "location.driver.preferences.data.store")
}
