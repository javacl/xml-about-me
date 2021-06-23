package com.baloot.test.features.user.ui

import com.baloot.test.core.preferences.domain.GetIsDarkThemeFlowPrefs
import com.baloot.test.core.util.viewModel.BaseViewModel
import com.baloot.test.features.user.domain.GetUserProfileLocal
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
