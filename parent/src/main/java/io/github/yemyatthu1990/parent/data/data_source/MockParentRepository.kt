package io.github.yemyatthu1990.parent.data.data_source

import io.github.yemyatthu1990.parent.data.dto.App
import io.github.yemyatthu1990.parent.domain.repository.ParentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MockParentRepository(private val ioDispatcher: CoroutineDispatcher) : ParentRepository {
    override fun getChildInstalledApps(): Flow<App> {
        return flow<App> {
            (1..100).forEach {
                delay(100L)
                emit(App("Testing $it", it))
            }
        }
    }
}