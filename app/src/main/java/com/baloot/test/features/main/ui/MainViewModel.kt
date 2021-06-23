package com.baloot.test.features.main.ui

import androidx.lifecycle.viewModelScope
import com.baloot.test.core.preferences.domain.DoUpdateIsDarkThemePrefs
import com.baloot.test.core.preferences.domain.GetIsDarkThemePrefs
import com.baloot.test.core.util.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getIsDarkThemePrefs: GetIsDarkThemePrefs,
    private val doUpdateIsDarkThemePrefs: DoUpdateIsDarkThemePrefs
) : BaseViewModel() {

    fun isDarkTheme() = getIsDarkThemePrefs()

    fun changeTheme(value: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            doUpdateIsDarkThemePrefs(value)
        }
    }

    override fun getData() {}
}
