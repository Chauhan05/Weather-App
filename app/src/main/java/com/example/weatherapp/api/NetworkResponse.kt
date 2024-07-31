package com.example.weatherapp.api
// T is Weather Model
sealed class NetworkResponse<out T> {
    data class Success<out T>(val data: T) : NetworkResponse<T>()
    data class Error(val message: String) : NetworkResponse<Nothing>()
    object Loading:NetworkResponse<Nothing>()
}