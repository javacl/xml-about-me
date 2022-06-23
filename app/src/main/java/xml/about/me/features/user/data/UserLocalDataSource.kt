package xml.about.me.features.user.data

import xml.about.me.R
import xml.about.me.features.user.data.entities.UserProfileEntity
import xml.about.me.features.user.data.entities.UserProfileSocialNetworkEntity
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor() {

    fun getUserProfile() = flow {
        emit(
            UserProfileEntity(
                image = R.drawable.image_profile,
                fullName = R.string.label_full_name,
                socialNetwork = ArrayList<UserProfileSocialNetworkEntity>().apply {
                    add(
                        UserProfileSocialNetworkEntity(
                            title = R.string.label_github,
                            link = R.string.link_github
                        )
                    )
                    add(
                        UserProfileSocialNetworkEntity(
                            title = R.string.label_gitlab,
                            link = R.string.link_gitlab
                        )
                    )
                    add(
                        UserProfileSocialNetworkEntity(
                            title = R.string.label_linkedin,
                            link = R.string.link_linkedin
                        )
                    )
                    add(
                        UserProfileSocialNetworkEntity(
                            title = R.string.label_telegram,
                            link = R.string.link_telegram
                        )
                    )
                    add(
                        UserProfileSocialNetworkEntity(
                            title = R.string.label_instagram,
                            link = R.string.link_instagram
                        )
                    )
                }
            )
        )
    }
}
