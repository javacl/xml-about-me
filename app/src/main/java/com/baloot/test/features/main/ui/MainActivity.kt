package com.baloot.test.features.main.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavDirections
import com.baloot.test.core.util.ThemeUtils
import com.baloot.test.core.util.localizedContext
import com.baloot.test.core.util.showLongToast
import com.baloot.test.core.util.showShortToast
import com.baloot.test.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemReselectedListener,
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
            setOnNavigationItemSelectedListener(this@MainActivity)
            setOnNavigationItemReselectedListener(this@MainActivity)
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        mainNavigationManager.switchTab(item.itemId)
        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        mainNavigationManager.scrollToTop()
        mainNavigationManager.clearStack()
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
