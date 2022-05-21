package com.about.me.core.api

import com.about.me.core.exception.Exceptions
import com.about.me.core.model.ApiResult
import com.about.me.core.util.errorParser
import retrofit2.Response
import javax.inject.Inject

open class BaseRemoteDataSource {

    @Inject
    lateinit var urls: ApiUrlHelper

    protected fun <T> checkApiResult(response: Response<T>): ApiResult<T> {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null)
                return ApiResult.Success(body)
        }
        val error = errorParser(response.errorBody()?.string())
        return ApiResult.Error(
            Exceptions.RemoteDataSourceException(
                error.status,
                error.message,
                response.code()
            )
        )
    }
}
