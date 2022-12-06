package com.amary.core.constant

object BaseKey {
    init {
        System.loadLibrary("native-lib")
    }

    private external fun baseURL(): String
    private external fun apiKey(): String

    val BASE_URL = baseURL()
    val API_KEY = apiKey()
}