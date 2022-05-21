package com.about.me.features.article.ui

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.about.me.core.util.viewModel.BaseViewModel
import com.about.me.features.article.domain.GetArticleListLocal
import com.about.me.features.article.domain.GetArticleListRemote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    getArticleListLocal: GetArticleListLocal,
    private val getArticleListRemote: GetArticleListRemote
) : BaseViewModel() {

    private var _currentPage = 1
    val currentPage
        get() = _currentPage

    val articleList = getArticleListLocal().asLiveData(coroutineContext)

    init {
        getData()
    }

    override fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_currentPage > 1)
                networkMoreLoading(ArticleRequestTag.GetArticleList.name)
            else networkLoading(ArticleRequestTag.GetArticleList.name)
            observeNetworkState(
                getArticleListRemote(page = _currentPage),
                ArticleRequestTag.GetArticleList.name
            )
        }
    }

    override fun refresh() {
        _currentPage = 1
        getData()
    }

    fun getNextPage() {
        _currentPage++
        getData()
    }
}
