package xml.about.me.core.util

interface NetworkCallback {
    fun refresh()
    fun retry() = refresh()
}
