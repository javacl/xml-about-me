package xml.about.me.features.user.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import xml.about.me.R
import xml.about.me.core.util.addVerticalDividerSpacing4x
import xml.about.me.core.util.disableAnimationChanges
import xml.about.me.core.util.scrollToTop
import xml.about.me.core.util.ui.BaseFragment
import xml.about.me.databinding.FragmentUserProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>() {

    private val viewModel by viewModels<UserProfileViewModel>()

    private lateinit var userProfileSocialNetworkAdapter: UserProfileSocialNetworkAdapter

    override val layoutId: Int
        get() = R.layout.fragment_user_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValue()
        initView()
        initObservation()
    }

    private fun initValue() {}

    private fun initView() {

        binding.apply {

            ivTheme.setOnClickListener {
                mainHelper.changeTheme()
            }

            btnAboutMe.setOnClickListener {
                mainHelper.navigate(UserProfileFragmentDirections.actionUserProfileFragmentToAboutMeSheet())
            }
        }

        initRecyclerView()
    }

    private fun initObservation() {

        lifecycleScope.launch {

            viewModel.userProfile.collect {
                binding.userProfile = it
                userProfileSocialNetworkAdapter.submitList(it.socialNetwork)
            }

            viewModel.getIsDarkThemeFlow.collect {
                binding.isDarkTheme = it
            }
        }
    }

    private fun initRecyclerView() {

        userProfileSocialNetworkAdapter = UserProfileSocialNetworkAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(it.link))))
        }

        binding.rvSocialNetwork.apply {
            disableAnimationChanges()
            addVerticalDividerSpacing4x(requireContext())
            adapter = userProfileSocialNetworkAdapter
        }
    }

    override fun onScrollToTop() {
        binding.apply {
            nsvUserProfile.scrollToTop()
        }
    }
}
