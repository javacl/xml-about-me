package com.baloot.test.core.preferences.domain

import com.baloot.test.core.preferences.PreferencesDataStore
import javax.inject.Inject

class DoUpdateIsDarkThemePrefs @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) {
    suspend operator fun invoke(value: Boolean) = preferencesDataStore.updateIsDarkTheme(value)
}
