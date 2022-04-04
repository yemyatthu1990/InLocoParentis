package io.github.yemyatthu1990.appservice

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.yemyatthu1990.appservice.dto.App
import io.github.yemyatthu1990.appservice.listener.AppServiceListCallback
import io.github.yemyatthu1990.appservice.repository.AppRepository
import io.github.yemyatthu1990.appservice.repository.SystemAppRepositoryImpl
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class AppService private constructor(){
    @Inject private lateinit var systemAppRepository: AppRepository
    private val appServiceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    companion object {
        private var appServiceInstance: AppService? = null
        fun getInstance(): AppService {
            if (appServiceInstance == null)
                appServiceInstance = AppService()
            return appServiceInstance!!
        }
    }

    fun getInstalledApps(callback: AppServiceListCallback<App>){
        appServiceScope.launch {
            try {
                callback.onSuccess(systemAppRepository.getInstalledApps())
            } catch (e: Exception) {
                callback.onError(e)
            }
        }


    }

}