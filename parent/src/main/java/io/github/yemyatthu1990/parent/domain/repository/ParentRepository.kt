package io.github.yemyatthu1990.parent.domain.repository

import io.github.yemyatthu1990.parent.data.dto.App
import kotlinx.coroutines.flow.Flow

interface ParentRepository {
    fun getChildInstalledApps(): Flow<App>
}