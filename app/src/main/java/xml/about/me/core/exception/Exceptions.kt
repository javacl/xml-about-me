package xml.about.me.core.exception

sealed class Exceptions {

    data class IOException(val message: String = "IO Exception", val cause: Throwable) :
        Exceptions()

    data class NetworkConnectionException(val message: String = "Network Connection Error") :
        Exceptions()

    data class RemoteDataSourceException(
        val status: String?,
        val message: String?,
        val responseCode: Int
    ) : Exceptions()

    data class LocalDataSourceException(
        val message: String = "Local Data Source Error",
        val cause: Throwable? = null
    ) :
        Exceptions()

    data class InputDataException<T : Any>(val errors: List<T>) : Exceptions()

    data class ValidationException<T : Any>(val errors: T) : Exceptions()
}
