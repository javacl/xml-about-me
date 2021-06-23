package com.baloot.test.features.article.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.baloot.test.R
import com.baloot.test.core.util.scrollToTop
import com.baloot.test.core.util.ui.BaseFragment
import com.baloot.test.databinding.FragmentArticleBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
@AndroidEntryPoint
class ArticleFragment : BaseFragment<FragmentArticleBinding>() {

    private val params by navArgs<ArticleFragmentArgs>()

    private val viewModel by viewModels<ArticleViewModel>()

    override val layoutId: Int
        get() = R.layout.fragment_article

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValue()
        initView()
        initObservation()
    }

    private fun initValue() {

        viewModel.emitArticleId(params.articleId)
    }

    private fun initView() {

        binding.apply {

            toolbar.root.setOnClickListener {
                onScrollToTop()
            }

            toolbar.ivBack.setOnClickListener {
                mainHelper.goBack()
            }
        }
    }

    private fun initObservation() {

        lifecycleScope.launch {

            viewModel.article.collect {
                binding.article = it
            }
        }
    }

    override fun onScrollToTop() {
        binding.apply {
            nsvArticle.scrollToTop()
        }
    }
}
