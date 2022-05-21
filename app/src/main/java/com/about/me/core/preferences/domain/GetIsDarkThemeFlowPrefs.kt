package com.about.me.core.preferences.domain

import com.about.me.core.preferences.PreferencesDataStore
import javax.inject.Inject

class GetIsDarkThemeFlowPrefs @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) {
    operator fun invoke() = preferencesDataStore.getIsDarkThemeFlow
}
