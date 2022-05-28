package com.about.me.features.user.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.about.me.core.util.ui.BaseActivity
import com.about.me.databinding.ActivityStartBinding
import com.about.me.features.main.ui.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity : BaseActivity() {

    lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {

        binding = ActivityStartBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            delay(2000)
            startActivity(Intent(this@StartActivity, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            })
            finish()
        }
    }
}
