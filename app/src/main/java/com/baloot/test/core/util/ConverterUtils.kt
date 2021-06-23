package com.baloot.test.core.util

import com.baloot.test.core.di.AppJsonAdapterFactory
import com.squareup.moshi.Moshi

object ConverterUtils {

    val jsonConverter: Moshi = Moshi.Builder()
        .add(AppJsonAdapterFactory.INSTANCE)
        .build()
}
