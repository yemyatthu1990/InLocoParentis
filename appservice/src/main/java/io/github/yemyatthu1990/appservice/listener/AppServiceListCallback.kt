package io.github.yemyatthu1990.appservice.listener

interface AppServiceListCallback<T> {
    fun onSuccess(data: List<T>)
    fun onError(e: Exception)
}