package io.github.yemyatthu1990.parent.domain.use_case

import io.github.yemyatthu1990.parent.data.dto.App
import io.github.yemyatthu1990.parent.domain.repository.ParentRepository
import kotlinx.coroutines.flow.Flow

class GetApps(private val appRepository: ParentRepository) {

    operator fun invoke(): Flow<App> {
        return appRepository.getChildInstalledApps()
    }
}