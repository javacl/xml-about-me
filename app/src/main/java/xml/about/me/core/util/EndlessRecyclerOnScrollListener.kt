package xml.about.me.core.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener protected constructor() :
    RecyclerView.OnScrollListener() {

    private var mLoading = false // True if we are still waiting for the last set of data to load

    private var previousItemCount =
        0 // The total number of items in the dataSet after the last load

    var currentPage = 0
        private set // Always start at Page 1

    // Concrete classes should implement the Loading of more data entries
    abstract fun onLoadMore(current_page: Int)

    abstract fun onScrollTop(isVisible: Boolean)

    // When you're RecyclerView supports refreshing, also refresh the count
    fun refresh() {
        currentPage = 0
        previousItemCount = 0
        mLoading = false
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val mLinearLayoutManager: LinearLayoutManager = recyclerView
            .layoutManager as LinearLayoutManager

        val totalItemCount = mLinearLayoutManager.itemCount
        val lastCompletelyVisibleItemPosition =
            mLinearLayoutManager.findLastCompletelyVisibleItemPosition()

        if (mLoading) {

            // Check if current total is greater than previous
            if (totalItemCount > previousItemCount) {
                mLoading = false
                previousItemCount = totalItemCount
            }

        } else {

            // Check if the we've reached the end of the list
            if (lastCompletelyVisibleItemPosition == totalItemCount - 1 && totalItemCount != 0) {
                onLoadMore(++currentPage)
                mLoading = true
            }
        }

        if (dy > 0) {
            onScrollTop(!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN))
        } else {
            val visibleItemCount: Int = mLinearLayoutManager.childCount
            val firstVisibleItemPosition: Int = mLinearLayoutManager.findFirstVisibleItemPosition()
            val lastItem = firstVisibleItemPosition + visibleItemCount
            onScrollTop(lastItem > 5)
        }
    }
}
