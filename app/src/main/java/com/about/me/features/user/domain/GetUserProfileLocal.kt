package com.about.me.features.user.domain

import com.about.me.features.user.data.UserRepository
import javax.inject.Inject

class GetUserProfileLocal @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.getUserProfileLocal()
}
