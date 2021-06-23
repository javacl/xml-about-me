package com.baloot.test.features.user.ui

import com.baloot.test.core.util.viewModel.BaseViewModel
import com.baloot.test.features.user.domain.GetUserProfileLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    getUserProfileLocal: GetUserProfileLocal
) : BaseViewModel() {

    val userProfile = getUserProfileLocal()

    override fun getData() {}
}
