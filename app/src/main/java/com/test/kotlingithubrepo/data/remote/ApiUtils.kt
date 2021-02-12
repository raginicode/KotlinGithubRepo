package com.test.kotlingithubrepo.data.remote


object ApiUtils {

    val BASE_URL = "https://api.github.com/"

    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL).create(APIService::class.java)
}
