package xml.about.me.features.user.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) {
    fun getUserProfileLocal() = userLocalDataSource.getUserProfile()
}
