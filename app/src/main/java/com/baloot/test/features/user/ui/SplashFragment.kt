package com.baloot.test.features.user.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.baloot.test.R
import com.baloot.test.core.util.ui.BaseFragment
import com.baloot.test.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValue()
        initView()
        initObservation()
    }

    private fun initValue() {}

    private fun initView() {}

    private fun initObservation() {}

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            delay(2000)
            mainHelper.clearStack()
        }
    }
}
