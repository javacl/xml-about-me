package com.baloot.test.features.user.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.baloot.test.R
import com.baloot.test.core.util.addVerticalDividerSpacing4x
import com.baloot.test.core.util.autoCleared
import com.baloot.test.core.util.disableAnimationChanges
import com.baloot.test.core.util.scrollToTop
import com.baloot.test.core.util.ui.BaseFragment
import com.baloot.test.databinding.FragmentUserProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>() {

    private val viewModel by viewModels<UserProfileViewModel>()

    private var userProfileSocialNetworkAdapter by autoCleared<UserProfileSocialNetworkAdapter>()

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
