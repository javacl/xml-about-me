package com.baloot.test.core.preferences.domain

import com.baloot.test.core.preferences.PreferencesDataStore
import javax.inject.Inject

class GetIsDarkThemeFlowPrefs @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) {
    operator fun invoke() = preferencesDataStore.getIsDarkThemeFlow
}
