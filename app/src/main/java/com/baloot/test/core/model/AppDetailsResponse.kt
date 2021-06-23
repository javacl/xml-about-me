package com.baloot.test.core.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class AppDetailsResponse<out T>(
    override val status: String = "",
    val data: T
) : AppResponse()
