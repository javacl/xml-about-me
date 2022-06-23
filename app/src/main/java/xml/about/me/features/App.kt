package xml.about.me.features

import android.app.Application
import xml.about.me.core.preferences.domain.GetIsDarkThemePrefs
import xml.about.me.core.util.ThemeUtils
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var getIsDarkThemePrefs: GetIsDarkThemePrefs

    override fun onCreate() {
        super.onCreate()
        val isDarkTheme = getIsDarkThemePrefs()
        ThemeUtils.changeTheme(isDarkTheme)
    }
}
