package io.github.yemyatthu1990.appservice.repository

import android.content.Context
import io.github.yemyatthu1990.appservice.dto.App

interface AppRepository {
    suspend fun getInstalledApps(): List<App>
}