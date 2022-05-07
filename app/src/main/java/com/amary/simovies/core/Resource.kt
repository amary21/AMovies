package com.amary.simovies.core

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T) : Resource<T>(data)
    class Unauthorized<T>(message: String): Resource<T>(data = null, message =  message)
    class Failed<T>(message: String): Resource<T>(data = null, message = message)
}
