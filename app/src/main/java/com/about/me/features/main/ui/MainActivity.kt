package com.about.me.features.main.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import com.about.me.core.util.ThemeUtils
import com.about.me.core.util.localizedContext
import com.about.me.core.util.showLongToast
import com.about.me.core.util.showShortToast
import com.about.me.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
    MainHelper {

    lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    private val mainNavigationManager by lazy { MainNavigationManager(this) }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(localizedContext(context))
    }

    override fun onStart() {
        super.onStart()
        localizedContext(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initValue()
        initView(savedInstanceState)
        initObservation()
    }

    private fun initValue() {}

    private fun initView(savedInstanceState: Bundle?) {

        if (viewModel.isDarkTheme())
            darkStatusBar()
        else lightStatusBar()

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        if (savedInstanceState == null)
            mainNavigationManager.initTabManager()
        mainNavigationManager.initDestinationChangedListener()

        binding.bnvMain.apply {
            setOnItemSelectedListener {
                mainNavigationManager.switchTab(it.itemId)
                return@setOnItemSelectedListener true
            }
            setOnItemReselectedListener {
                mainNavigationManager.scrollToTop()
                mainNavigationManager.clearStack()
            }
        }
    }

    private fun initObservation() {}

    @Suppress("DEPRECATION")
    private fun lightStatusBar() {
        val view = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.systemUiVisibility =
                view.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    @Suppress("DEPRECATION")
    private fun darkStatusBar() {
        val view = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.systemUiVisibility =
                view.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mainNavigationManager.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mainNavigationManager.onRestoreInstanceState(savedInstanceState)
    }

    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mainNavigationManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun supportNavigateUpTo(upIntent: Intent) {
        mainNavigationManager.supportNavigateUpTo()
    }

    override fun onBackPressed() {
        mainNavigationManager.onBackPressed()
    }

    override fun navigate(direction: NavDirections) {
        mainNavigationManager.navigate(direction)
    }

    override fun navigateSinglePage(direction: NavDirections, finish: Boolean) {
        mainNavigationManager.navigateSinglePage(direction, finish)
    }

    override fun clearStack(tag: MainNavigationTag) {
        mainNavigationManager.clearStack(tag)
    }

    override fun goBack(tag: MainNavigationTag, number: Int) {
        mainNavigationManager.goBack(tag, number)
    }

    override fun switchTab(tag: MainNavigationTag) {
        mainNavigationManager.switchTab(tag)
    }

    override fun showLongMessage(resourceId: Int) {
        showLongToast(resourceId)
    }

    override fun showLongMessage(message: String) {
        showLongToast(message)
    }

    override fun showShortMessage(resourceId: Int) {
        showShortToast(resourceId)
    }

    override fun showShortMessage(message: String) {
        showShortToast(message)
    }

    override fun showRemoteMessage(serverErrorMessage: String?, errorMessage: Int) {
        if (serverErrorMessage == null) {
            if (errorMessage != 0)
                showLongMessage(errorMessage)
        } else showLongMessage(serverErrorMessage)
    }

    override fun changeTheme() {
        val isDarkTheme = viewModel.isDarkTheme()
        viewModel.changeTheme(!isDarkTheme)
        ThemeUtils.changeTheme(!isDarkTheme)
    }
}
