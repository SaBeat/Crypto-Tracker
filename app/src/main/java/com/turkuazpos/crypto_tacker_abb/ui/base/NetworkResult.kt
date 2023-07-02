package com.turkuazpos.crypto_tacker_abb.ui.base

sealed class NetworkResult<T>(
    val data: T? = null, val message: String? = null
) {
    class Empty<T> : NetworkResult<T>()
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String?, code: Int, data: T? = null) : NetworkResult<T>(data, message)
}
