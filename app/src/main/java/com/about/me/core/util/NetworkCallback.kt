package com.about.me.core.util

interface NetworkCallback {
    fun refresh()
    fun retry() = refresh()
}
