package com.baloot.test.features.user.data

import com.baloot.test.R
import com.baloot.test.features.user.data.entities.UserProfileEntity
import com.baloot.test.features.user.data.entities.UserProfileSocialNetworkEntity
import com.baloot.test.features.user.ui.UserProfileSocialNetworkTag
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
                            tag = UserProfileSocialNetworkTag.Github,
                            title = R.string.label_github,
                            link = R.string.link_github
                        )
                    )
                    add(
                        UserProfileSocialNetworkEntity(
                            tag = UserProfileSocialNetworkTag.Gitlab,
                            title = R.string.label_gitlab,
                            link = R.string.link_gitlab
                        )
                    )
                    add(
                        UserProfileSocialNetworkEntity(
                            tag = UserProfileSocialNetworkTag.Telegram,
                            title = R.string.label_telegram,
                            link = R.string.link_telegram
                        )
                    )
                    add(
                        UserProfileSocialNetworkEntity(
                            tag = UserProfileSocialNetworkTag.Instagram,
                            title = R.string.label_instagram,
                            link = R.string.link_instagram
                        )
                    )
                }
            )
        )
    }
}
