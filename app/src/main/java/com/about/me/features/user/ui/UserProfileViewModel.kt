package com.about.me.features.user.ui

import com.about.me.core.preferences.domain.GetIsDarkThemeFlowPrefs
import com.about.me.core.util.viewModel.BaseViewModel
import com.about.me.features.user.domain.GetUserProfileLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    getUserProfileLocal: GetUserProfileLocal,
    getIsDarkThemeFlowPrefs: GetIsDarkThemeFlowPrefs
) : BaseViewModel() {

    val userProfile = getUserProfileLocal()

    val getIsDarkThemeFlow = getIsDarkThemeFlowPrefs()

    override fun getData() {}
}
