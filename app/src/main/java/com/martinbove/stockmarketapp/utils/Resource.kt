package com.martinbove.stockmarketapp.utils

/**
 * Created by Martín Bove on 10/08/2023.
 * E-mail: mbove77@gmail.com
 */

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data:T? = null ): Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>(null)
}
