package xml.about.me.core.util

import androidx.recyclerview.widget.RecyclerView

fun initRecyclerViewAdapterDataObserver(callback: () -> Unit): RecyclerView.AdapterDataObserver {

    return object : RecyclerView.AdapterDataObserver() {

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            callback()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            callback()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            callback()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            callback()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            callback()
        }
    }
}
