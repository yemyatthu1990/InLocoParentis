package io.github.yemyatthu1990.parent.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.yemyatthu1990.parent.nav.ParentNavigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun ParentNavigationComponent() {
    val navigator = ParentNavigator()
    val navController = rememberNavController()
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach {
            navController.navigate(it)
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = ParentNavigator::class.java.simpleName + "home"
    ) {
        composable(ParentNavigator::class.java.simpleName + "home") {
            Greeting("from Child Home", "Detail"){
                navigator.navigateTo(ParentNavigator::class.java.simpleName  +"detail")
            }
        }
        composable(ParentNavigator::class.java.simpleName + "home") {
            Greeting("from Child Detail", "Home"){
                navigator.navigateTo(ParentNavigator::class.java.simpleName  +"home")
            }
        }
    }
}

@Composable
fun Greeting(label: String, buttonLabel: String, onClick: () -> Unit) {
    Column() {
        Text(text = "Greeting $label")
        Button(onClick = onClick) {
            Text("go to $buttonLabel")
        }
    }
}