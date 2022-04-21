package com.baloot.test.features.main.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.baloot.test.R
import com.baloot.test.core.util.safeNavigate
import com.baloot.test.core.util.ui.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainNavigationManager(private val mainActivity: MainActivity) {

    private val startDestinations = mapOf(
        R.id.navigation_emptyFragment to R.id.navigation_emptyFragment,
        R.id.navigation_articleListFragment to R.id.navigation_articleListFragment,
        R.id.navigation_userProfileFragment to R.id.navigation_userProfileFragment
    )

    private var currentNavigationId: Int = R.id.navigation_emptyFragment
    private var currentNavigationController: NavController? = null

    private var navEmptyControllerIsCreated = false
    private val navEmptyController by lazy {
        mainActivity.findNavController(R.id.nav_empty).apply {
            graph = navInflater.inflate(R.navigation.main_navigation).apply {
                setStartDestination(startDestinations.getValue(R.id.navigation_emptyFragment))
            }
        }
    }
    private var navArticleListControllerIsCreated = false
    private val navArticleListController by lazy {
        mainActivity.findNavController(R.id.nav_articleList).apply {
            graph = navInflater.inflate(R.navigation.main_navigation).apply {
                setStartDestination(startDestinations.getValue(R.id.navigation_articleListFragment))
            }
        }
    }
    private var navUserProfileControllerIsCreated = false
    private val navUserProfileController by lazy {
        mainActivity.findNavController(R.id.nav_userProfile).apply {
            graph = navInflater.inflate(R.navigation.main_navigation).apply {
                setStartDestination(startDestinations.getValue(R.id.navigation_userProfileFragment))
            }
        }
    }

    private val emptyContainer: View by lazy { mainActivity.binding.containerEmpty }
    private val articleListContainer: View by lazy { mainActivity.binding.containerArticleList }
    private val userProfileContainer: View by lazy { mainActivity.binding.containerUserProfile }
    private val viewDividerMain: View by lazy { mainActivity.binding.viewDividerMain }
    private val bnvMain: BottomNavigationView by lazy { mainActivity.binding.bnvMain }

    fun initTabManager() {
        currentNavigationController = navEmptyController
        navigateSinglePage(EmptyFragmentDirections.actionEmptyFragmentToSplashFragment())
    }

    fun initDestinationChangedListener() {
        navEmptyController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_emptyFragment -> {
                    switchTab(bnvMain.selectedItemId)
                }
                else -> {
                    switchTab(startDestinations.getValue(R.id.navigation_emptyFragment))
                }
            }
        }
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(KEY_EMPTY_NAVIGATION_CREATED, navEmptyControllerIsCreated)
        outState.putBoolean(KEY_ARTICLE_LIST_NAVIGATION_CREATED, navArticleListControllerIsCreated)
        outState.putBoolean(KEY_USER_PROFILE_NAVIGATION_CREATED, navUserProfileControllerIsCreated)

        if (navEmptyControllerIsCreated)
            outState.putBundle(KEY_EMPTY_NAVIGATION_STATE, navEmptyController.saveState())
        if (navArticleListControllerIsCreated)
            outState.putBundle(
                KEY_ARTICLE_LIST_NAVIGATION_STATE,
                navArticleListController.saveState()
            )
        if (navUserProfileControllerIsCreated)
            outState.putBundle(
                KEY_USER_PROFILE_NAVIGATION_STATE,
                navUserProfileController.saveState()
            )

        outState.putSerializable(KEY_CURRENT_NAV_ID, currentNavigationId)
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            navEmptyControllerIsCreated = it.getBoolean(KEY_EMPTY_NAVIGATION_CREATED)
            navArticleListControllerIsCreated = it.getBoolean(KEY_ARTICLE_LIST_NAVIGATION_CREATED)
            navUserProfileControllerIsCreated = it.getBoolean(KEY_USER_PROFILE_NAVIGATION_CREATED)

            if (navEmptyControllerIsCreated)
                navEmptyController.restoreState(it.getBundle(KEY_EMPTY_NAVIGATION_STATE))
            if (navArticleListControllerIsCreated)
                navArticleListController.restoreState(it.getBundle(KEY_ARTICLE_LIST_NAVIGATION_STATE))
            if (navUserProfileControllerIsCreated)
                navUserProfileController.restoreState(it.getBundle(KEY_USER_PROFILE_NAVIGATION_STATE))

            currentNavigationId = it.getSerializable(KEY_CURRENT_NAV_ID) as Int
            switchTab(currentNavigationId)
        }
    }

    @Suppress("DEPRECATION")
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        currentNavigationController?.let {
            val emptyNavHostFragment =
                mainActivity.supportFragmentManager.findFragmentById(R.id.nav_empty) as NavHostFragment?
            emptyNavHostFragment?.let {
                it.childFragmentManager.fragments.forEach { fragment ->
                    fragment.onActivityResult(requestCode, resultCode, data)
                }
            }
            val articleListNavHostFragment =
                mainActivity.supportFragmentManager.findFragmentById(R.id.nav_articleList) as NavHostFragment?
            articleListNavHostFragment?.let {
                it.childFragmentManager.fragments.forEach { fragment ->
                    fragment.onActivityResult(requestCode, resultCode, data)
                }
            }
            val userProfileNavHostFragment =
                mainActivity.supportFragmentManager.findFragmentById(R.id.nav_userProfile) as NavHostFragment?
            userProfileNavHostFragment?.let {
                it.childFragmentManager.fragments.forEach { fragment ->
                    fragment.onActivityResult(requestCode, resultCode, data)
                }
            }
        }
    }

    fun supportNavigateUpTo() {
        currentNavigationController?.navigateUp()
    }

    fun onBackPressed() {
        currentNavigationController?.let {
            if (it.currentDestination == null || it.currentDestination?.id == R.id.navigation_splashFragment) mainActivity.finish()
            else if (
                it.currentDestination == null || it.currentDestination?.id ==
                startDestinations.getValue(currentNavigationId)
            ) {
                if (it.currentDestination?.id == startDestinations.getValue(R.id.navigation_articleListFragment))
                    mainActivity.finish()
                else {
                    switchToArticleListFragment()
                }
            } else it.popBackStack()
        } ?: run {
            mainActivity.finish()
        }
    }

    fun switchTab(tag: MainNavigationTag) {
        when (tag) {
            MainNavigationTag.Default -> {
            }
            MainNavigationTag.Empty -> {
            }
            MainNavigationTag.ArticleList -> switchToArticleListFragment()
            MainNavigationTag.UserProfile -> switchToUserProfileFragment()
        }
    }

    fun switchTab(tabId: Int) {
        currentNavigationId = tabId

        when (tabId) {
            R.id.navigation_emptyFragment -> {
                currentNavigationController = navEmptyController
                navEmptyControllerIsCreated = true
                invisibleTabContainerExcept(emptyContainer, true)
            }
            R.id.navigation_articleListFragment -> {
                currentNavigationController = navArticleListController
                navArticleListControllerIsCreated = true
                invisibleTabContainerExcept(articleListContainer, false)
            }
            R.id.navigation_userProfileFragment -> {
                currentNavigationController = navUserProfileController
                navUserProfileControllerIsCreated = true
                invisibleTabContainerExcept(userProfileContainer, false)
            }
        }
    }

    private fun switchToArticleListFragment() {
        switchTab(startDestinations.getValue(R.id.navigation_articleListFragment))
        bnvMain.menu.findItem(R.id.navigation_articleListFragment)?.isChecked = true
    }

    private fun switchToUserProfileFragment() {
        switchTab(startDestinations.getValue(R.id.navigation_userProfileFragment))
        bnvMain.menu.findItem(R.id.navigation_userProfileFragment)?.isChecked = true
    }

    fun clearStack(tag: MainNavigationTag = MainNavigationTag.Default) {
        when (tag) {
            MainNavigationTag.Default -> currentNavigationController?.graph?.findStartDestination()
                ?.let {
                    currentNavigationController?.popBackStack(
                        it.id,
                        false
                    )
                }
            MainNavigationTag.Empty -> navEmptyController.graph.findStartDestination().let {
                currentNavigationController?.popBackStack(
                    it.id,
                    false
                )
            }
            MainNavigationTag.ArticleList -> navArticleListController.graph.findStartDestination()
                .let {
                    currentNavigationController?.popBackStack(
                        it.id,
                        false
                    )
                }
            MainNavigationTag.UserProfile -> navUserProfileController.graph.findStartDestination()
                .let {
                    currentNavigationController?.popBackStack(
                        it.id,
                        false
                    )
                }
        }
    }

    fun scrollToTop() {
        currentNavigationController?.currentDestination?.id?.let {

            when (it) {

                R.id.navigation_articleListFragment -> {
                    (mainActivity.supportFragmentManager.findFragmentById(R.id.nav_articleList) as NavHostFragment?)?.let { navHostFragment ->
                        navHostFragment.childFragmentManager.fragments.forEach { fragment ->
                            if (fragment is BaseFragment<*>)
                                fragment.onScrollToTop()
                        }
                    }
                }
                R.id.navigation_userProfileFragment -> {
                    (mainActivity.supportFragmentManager.findFragmentById(R.id.nav_userProfile) as NavHostFragment?)?.let { navHostFragment ->
                        navHostFragment.childFragmentManager.fragments.forEach { fragment ->
                            if (fragment is BaseFragment<*>)
                                fragment.onScrollToTop()
                        }
                    }
                }
                else -> {
                }
            }
        }
    }

    fun goBack(tag: MainNavigationTag = MainNavigationTag.Default, number: Int = 1) {
        mainActivity.lifecycleScope.launch(Dispatchers.Main) {
            repeat(number) {
                when (tag) {
                    MainNavigationTag.Default -> onBackPressed()
                    MainNavigationTag.Empty -> navEmptyController.popBackStack()
                    MainNavigationTag.ArticleList -> navArticleListController.popBackStack()
                    MainNavigationTag.UserProfile -> navUserProfileController.popBackStack()
                }
            }
        }
    }

    private fun invisibleTabContainerExcept(container: View, hideNavigation: Boolean) {
        emptyContainer.visibility = View.GONE
        articleListContainer.visibility = View.GONE
        userProfileContainer.visibility = View.GONE

        container.visibility = View.VISIBLE
        if (hideNavigation) {
            viewDividerMain.visibility = View.GONE
            bnvMain.visibility = View.GONE
        } else {
            viewDividerMain.visibility = View.VISIBLE
            bnvMain.visibility = View.VISIBLE
        }

    }

    fun navigate(directions: NavDirections) {
        currentNavigationController?.let {
            safeNavigate(it, directions)
        }
    }

    fun navigateSinglePage(directions: NavDirections, finish: Boolean = false) {
        if (finish) onBackPressed()
        safeNavigate(navEmptyController, directions)
    }

    companion object {
        private const val KEY_EMPTY_NAVIGATION_CREATED = "key_empty_navigation_created"
        private const val KEY_EMPTY_NAVIGATION_STATE = "key_empty_navigation_state"

        private const val KEY_ARTICLE_LIST_NAVIGATION_CREATED =
            "key_article_list_navigation_created"
        private const val KEY_ARTICLE_LIST_NAVIGATION_STATE = "key_article_list_navigation_state"

        private const val KEY_USER_PROFILE_NAVIGATION_CREATED =
            "key_user_profile_navigation_created"
        private const val KEY_USER_PROFILE_NAVIGATION_STATE = "key_user_profile_navigation_state"

        private const val KEY_CURRENT_NAV_ID = "key_current_navigation_id"
    }
}
