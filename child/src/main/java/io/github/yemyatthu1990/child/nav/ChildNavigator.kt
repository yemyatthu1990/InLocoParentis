package io.github.yemyatthu1990.child.nav

import androidx.annotation.StringDef
import io.github.yemyatthu1990.common_arch.Route
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class ChildNavigator: Route {
    private val _sharedFlow = 
      MutableSharedFlow<String>(extraBufferCapacity = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    override fun navigateTo(label: String) {
        _sharedFlow.tryEmit(label)
    }
}