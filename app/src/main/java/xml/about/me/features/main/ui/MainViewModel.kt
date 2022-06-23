package xml.about.me.features.main.ui

import androidx.lifecycle.viewModelScope
import xml.about.me.core.preferences.domain.DoUpdateIsDarkThemePrefs
import xml.about.me.core.preferences.domain.GetIsDarkThemePrefs
import xml.about.me.core.util.viewModel.BaseViewModel
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
