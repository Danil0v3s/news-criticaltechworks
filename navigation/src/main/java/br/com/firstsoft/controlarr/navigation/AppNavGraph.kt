package br.com.firstsoft.controlarr.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.firstsoft.core.ui.animatedComposable
import br.com.firstsoft.feature.main.ui.MainScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
    actions: NavActions = remember(navController) {
        NavActions(navController)
    }
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier
    ) {
        animatedComposable(route = Screen.Main.route) {
            MainScreen(onItemSelected = {
            })
        }
    }
}
