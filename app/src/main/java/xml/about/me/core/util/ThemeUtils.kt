package xml.about.me.core.util

import androidx.appcompat.app.AppCompatDelegate

object ThemeUtils {

    fun changeTheme(isDarkTheme: Boolean) {
        if (isDarkTheme)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
