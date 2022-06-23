package xml.about.me.core.preferences.domain

import xml.about.me.core.preferences.PreferencesDataStore
import javax.inject.Inject

class GetIsDarkThemePrefs @Inject constructor(
    private val preferencesDataStore: PreferencesDataStore
) {
    operator fun invoke() = preferencesDataStore.getIsDarkTheme()
}
