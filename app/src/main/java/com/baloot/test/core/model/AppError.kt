package com.baloot.test.core.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class AppError(
    val status: String = "",
    val message: String = ""
)
