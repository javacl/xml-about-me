package com.baloot.test.core.util

interface NetworkCallback {
    fun refresh()
    fun retry() = refresh()
}
