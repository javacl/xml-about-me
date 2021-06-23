package com.baloot.test.features.user.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.baloot.test.R
import com.baloot.test.core.util.scrollToTop
import com.baloot.test.core.util.ui.BaseFragment
import com.baloot.test.databinding.FragmentUserProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserProfileFragment : BaseFragment<FragmentUserProfileBinding>() {

    private val viewModel by viewModels<UserProfileViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_user_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValue()
        initView()
        initObservation()
    }

    private fun initValue() {}

    private fun initView() {}

    private fun initObservation() {

        lifecycleScope.launch {

            viewModel.userProfile.collect {
                binding.userProfile = it
            }
        }
    }

    override fun onScrollToTop() {
        binding.apply {
            nsvUserProfile.scrollToTop()
        }
    }
}
