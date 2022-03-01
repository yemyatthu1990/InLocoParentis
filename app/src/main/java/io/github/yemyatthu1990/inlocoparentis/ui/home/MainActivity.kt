package io.github.yemyatthu1990.inlocoparentis.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.yemyatthu1990.child.nav.ChildNavigator
import io.github.yemyatthu1990.child.ui.home.ChildNavigationComponent
import io.github.yemyatthu1990.child.ui.home.Greeting
import io.github.yemyatthu1990.common.ui.theme.InLocoParentisTheme
import io.github.yemyatthu1990.inlocoparentis.nav.MainNavigator
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navigator = MainNavigator()
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NavigationComponent(navController,navigator)
                }
            }
        }
    }
}

@Composable
fun MainComponentChooser(navigator: MainNavigator) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        MainComponentButton(label = "Parent") {
            Toast.makeText(context, "parent clicking ", Toast.LENGTH_LONG)
                .show()
        }
        Spacer(modifier = Modifier.height(10.dp))
        MainComponentButton(label = "Child") {
            navigator.navigateTo("childHome")
        }
    }
}

@Composable
fun MainComponentButton(label: String, onClick: () -> Unit) {
    Button(contentPadding = PaddingValues(5.dp),onClick = onClick){
        Text(text = label)
    }
}

@Composable
fun NavigationComponent(navController: NavHostController, navigator: MainNavigator) {
    LaunchedEffect("navigation") {
        navigator.sharedFlow.onEach {
            navController.navigate(it)
        }.launchIn(this)
    }
    NavHost(
        navController = navController,
        startDestination = "mainHome"
    ) {
        composable("mainHome") {
            MainComponentChooser(navigator)
        }
        composable("childHome") {
            ChildNavigationComponent()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        val navigator = MainNavigator()
        MainComponentChooser(navigator)
    }
}
