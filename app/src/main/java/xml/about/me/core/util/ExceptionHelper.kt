package xml.about.me.core.util

import xml.about.me.R
import xml.about.me.core.exception.Exceptions
import timber.log.Timber

object ExceptionHelper {

    fun getError(exception: Exceptions): ErrorView {

        val serverErrorMessage: String?
        val message: Int
        val unauthorized: Boolean
        val icon: Int
        Timber.d(exception.toString())

        when (exception) {
            is Exceptions.IOException -> {
                serverErrorMessage = null
                message = R.string.error_server
                unauthorized = false
                icon = R.drawable.ic_connection_error
            }
            is Exceptions.NetworkConnectionException -> {
                serverErrorMessage = null
                message = R.string.error_connection
                unauthorized = false
                icon = R.drawable.ic_connection_error
            }
            is Exceptions.LocalDataSourceException -> {
                serverErrorMessage = null
                message = R.string.error_general
                unauthorized = false
                icon = R.drawable.ic_general_error
            }
            is Exceptions.RemoteDataSourceException -> {
                serverErrorMessage =
                    if (exception.message.isNullOrEmpty()) null else exception.message
                message = R.string.error_server
                unauthorized = unauthorized(exception.responseCode)
                icon = R.drawable.ic_connection_error
            }
            else -> {
                serverErrorMessage = null
                message = R.string.error_general
                unauthorized = false
                icon = R.drawable.ic_general_error
            }
        }
        return ErrorView(
            serverErrorMessage = serverErrorMessage,
            message = message,
            unauthorized = unauthorized,
            icon = icon
        )
    }

    private fun unauthorized(responseCode: Int): Boolean {
        return responseCode == 401
    }

    data class ErrorView(
        val serverErrorMessage: String?,
        val message: Int,
        val unauthorized: Boolean,
        val icon: Int
    )
}
