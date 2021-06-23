package com.baloot.test.features.user.domain

import com.baloot.test.features.user.data.UserRepository
import javax.inject.Inject

class GetUserProfileLocal @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = userRepository.getUserProfileLocal()
}
