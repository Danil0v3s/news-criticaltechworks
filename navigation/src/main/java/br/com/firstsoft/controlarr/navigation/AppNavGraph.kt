package br.com.firstsoft.controlarr.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import br.com.firstsoft.core.ui.animatedComposable
import br.com.firstsoft.feature.detail.ui.DetailScreen
import br.com.firstsoft.feature.home.ui.HomeScreen
import br.com.firstsoft.feature.news.api.model.Article
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph(
    moshi: Moshi,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
    actions: NavActions = remember(navController) {
        NavActions(navController)
    }
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        animatedComposable(route = Screen.Home.route) {
            HomeScreen(onItemSelected = {
                val article = moshi.adapter<Article>().toJson(it)
                navController.navigate(Screen.Detail.route.replace("{article}", article))
            })
        }
        animatedComposable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument("article") {
                    type = NavType.StringType
                }
            )
        ) {
            val articleJson = it.arguments?.getString("article")

            if (articleJson == null) {
                navController.navigateUp()
                return@animatedComposable
            }

            moshi.adapter<Article>().fromJson(articleJson)?.let { DetailScreen(it) }
        }
    }
}
