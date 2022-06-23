package xml.about.me.features.article.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import xml.about.me.databinding.ItemArticleListBinding
import xml.about.me.databinding.ItemLoadingMoreStateBinding
import xml.about.me.features.article.data.entities.ArticleEntity

class ArticleListAdapter(
    private val itemCallBackClick: (ArticleEntity) -> Unit
) : ListAdapter<ArticleEntity, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<ArticleEntity>() {
        override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
            return oldItem == newItem
        }
    }
) {
    companion object {
        const val VIEW_NORMAL = 0
        const val VIEW_LOADING = 1
    }

    private var isNetworkStateLoading: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_NORMAL -> {
                val view = ItemArticleListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ArticleListViewHolder(view)
            }
            VIEW_LOADING -> {
                val view = ItemLoadingMoreStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ArticleListLoadingViewHolder(view)
            }
            else -> throw IllegalStateException("Ops")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArticleListViewHolder)
            holder.onBind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return if (isNetworkStateLoading && position == itemCount - 1) VIEW_LOADING else VIEW_NORMAL
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (isNetworkStateLoading) 1 else 0
    }

    fun setLoadingState(isNetworkStateLoading: Boolean) {

        val previousState = this.isNetworkStateLoading

        this.isNetworkStateLoading = isNetworkStateLoading

        if (previousState != isNetworkStateLoading) {
            if (isNetworkStateLoading)
                notifyItemInserted(super.getItemCount())
            else
                notifyItemRemoved(super.getItemCount())
        }
    }

    inner class ArticleListViewHolder(private val binding: ItemArticleListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ArticleEntity) {
            binding.item = item
            binding.root.setOnClickListener {
                itemCallBackClick.invoke(item)
            }
        }
    }

    class ArticleListLoadingViewHolder(binding: ItemLoadingMoreStateBinding) :
        RecyclerView.ViewHolder(binding.root)
}
