package com.baloot.test.features

import android.app.Application
import com.baloot.test.core.preferences.domain.GetIsDarkThemePrefs
import com.baloot.test.core.util.ThemeUtils
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
