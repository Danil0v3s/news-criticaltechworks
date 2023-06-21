package br.com.firstsoft.controlarr.navigation

sealed class Screen(val name: String, val route: String) {
    object Splash : Screen("splash", "splash")
    object Main : Screen("main", "main")
    object Detail : Screen("detail", "detail?article={article}")
}
