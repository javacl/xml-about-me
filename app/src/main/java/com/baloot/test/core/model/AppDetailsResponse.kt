package com.baloot.test.core.model

data class AppDetailsResponse<out T>(
    override val status: String = "",
    val data: T
) : AppResponse()
