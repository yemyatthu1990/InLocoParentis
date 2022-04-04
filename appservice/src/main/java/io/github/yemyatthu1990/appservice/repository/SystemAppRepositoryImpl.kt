package io.github.yemyatthu1990.appservice.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.yemyatthu1990.appservice.dto.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SystemAppRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context): AppRepository {
    @SuppressLint("QueryPermissionsNeeded")
    override suspend fun getInstalledApps(): List<App> {
        return withContext(Dispatchers.IO) {
             context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA).map {
                App(it.name, it.labelRes)
            }
        }
    }
}