package com.baloot.test.core.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class AppListResponse<out T>(
    override val status: String = "",
    val data: List<T> = ArrayList()
) : AppResponse()
