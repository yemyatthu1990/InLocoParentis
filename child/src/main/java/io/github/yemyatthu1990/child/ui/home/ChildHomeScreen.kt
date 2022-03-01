package io.github.yemyatthu1990.child.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.yemyatthu1990.child.nav.ChildNavigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun ChildNavigationComponent() {
    val navigator = ChildNavigator()
    val navController = rememberNavController()
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach {
            navController.navigate(it)
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = ChildNavigator::class.java.simpleName + "home"
    ) {
        composable(ChildNavigator::class.java.simpleName + "home") {
            Greeting("from Child Home", "Detail"){
                navigator.navigateTo(ChildNavigator::class.java.simpleName  +"detail")
            }
        }
        composable(ChildNavigator::class.java.simpleName + "detail") {
            Greeting("from Child Detail", "Home" ){
                navigator.navigateTo(ChildNavigator::class.java.simpleName  +"home")
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