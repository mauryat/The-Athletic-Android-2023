package com.theathletic.interview.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.theathletic.interview.R
import com.theathletic.interview.articles.ui.ArticlesScreen
import com.theathletic.interview.articles.ui.ArticlesViewModel
import com.theathletic.interview.articles.ui.SingleArticleScreen
import com.theathletic.interview.leagues.LeaguesViewModel
import com.theathletic.interview.leagues.ui.LeaguesScreen
import com.theathletic.interview.ui.theme.AthleticTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val articlesViewModel: ArticlesViewModel by viewModel()
    private val leaguesViewModel: LeaguesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AthleticTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreenView()
                }
            }
        }
    }

    @Composable
    fun MainScreenView() {
        var selectedScreen by remember { mutableStateOf(Screen.Articles as Screen) }
        val navController = rememberNavController()

        Scaffold(bottomBar = {
            BottomNavigation(
                selectedScreen,
            ) {
                screen -> selectedScreen = screen
                navController
                    .navigateSingleTopTo(screen.route)
            }
        }) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Articles.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(
                    route = Screen.Articles.route) {
                    ArticlesScreen(
                        articlesViewModel,
                        navController,
                        onSingleArticleClick = { body, authorName, authorImageUrl, articleImageUrl, title ->
                            navController.navigateToSingleArticle(body, authorName, authorImageUrl, articleImageUrl, title)
                        }
                    )
                }
                composable(
                    route = Screen.Leagues.route) {
                    LeaguesScreen(
                        leaguesViewModel,
                        navController,
                        onSingleLeagueClick = { leagueImageUrl, title ->
                            navController.navigateToSingleLeague(
                                leagueImageUrl,
                                title
                            )
                        }
                    )
                }
                composable(
                    route = Screen.SingleArticle.routeWithArgs,
                    arguments = Screen.SingleArticle.arguments
                ) { navBackStackEntry ->
                    val body =
                        navBackStackEntry.arguments?.getString(Screen.SingleArticle.bodyArg)
                    val authorName =
                        navBackStackEntry.arguments?.getString(Screen.SingleArticle.authorNameArg)
                    val authorImageUrl =
                        navBackStackEntry.arguments?.getString(Screen.SingleArticle.authorImageUrlArg)
                    val articleImageUrl =
                        navBackStackEntry.arguments?.getString(Screen.SingleArticle.articleImageUrlArg)
                    val title =
                        navBackStackEntry.arguments?.getString(Screen.SingleArticle.titleArg)

                    SingleArticleScreen(body, authorName, authorImageUrl, articleImageUrl, title)
                }
                composable(
                    route = Screen.SingleLeague.routeWithArgs,
                    arguments = Screen.SingleLeague.arguments
                ) { navBackStackEntry ->
                    val leagueImageUrl =
                        navBackStackEntry.arguments?.getString(Screen.SingleLeague.leagueImageUrlArg)
                    val title =
                        navBackStackEntry.arguments?.getString(Screen.SingleLeague.titleArg)
                    // TODO: navigate to articles screen
                }
            }
        }
    }

    @Composable
    fun BottomNavigation(selectedScreen: Screen, onScreenSelected: (Screen) -> Unit) {
        val items = listOf(Screen.Articles, Screen.Leagues)
        BottomNavigation {
            items.forEachIndexed { index, item ->
                BottomNavigationItem(selected = item.route == selectedScreen.route,
                    icon = {
                        Icon(
                            painterResource(id = item.resourceIcon), contentDescription = getString(item.resourceTitle)
                        )
                    },
                    label = { Text(text = getString(item.resourceTitle), fontSize = 10.sp) },
                    onClick = { onScreenSelected(item) })
            }
        }
    }

    sealed class Screen(
        @StringRes val resourceTitle: Int, @DrawableRes val resourceIcon: Int,
        val route: String
    ) {
        object Articles : Screen(R.string.title_articles, R.drawable.ic_articles, "articles")
        object Leagues : Screen(R.string.title_leagues, R.drawable.ic_leagues, "leagues")
        object SingleArticle : Screen(R.string.title_single_article, R.drawable.ic_articles, "single_article") {
            const val titleArg = "title"
            const val articleImageUrlArg = "article_image_url"
            const val authorImageUrlArg = "author_image_url"
            const val authorNameArg = "author_name"
            const val bodyArg = "body"
            val routeWithArgs = "${route}/{${bodyArg}}/{${authorNameArg}}/{${authorImageUrlArg}}/{${articleImageUrlArg}}/{${titleArg}}"
            val arguments = listOf(
                navArgument(bodyArg) { type = NavType.StringType },
                navArgument(authorNameArg) { type = NavType.StringType },
                navArgument(authorImageUrlArg) { type = NavType.StringType },
                navArgument(articleImageUrlArg) { type = NavType.StringType },
                navArgument(titleArg) { type = NavType.StringType }
            )
        }

        object SingleLeague : Screen(R.string.title_single_league, R.drawable.ic_articles, "single_league") {
            const val titleArg = "title"
            const val leagueImageUrlArg = "league_image_url"
            val routeWithArgs = "${route}/{${leagueImageUrlArg}}/{${titleArg}}"
            val arguments = listOf(
                navArgument(leagueImageUrlArg) { type = NavType.StringType },
                navArgument(titleArg) { type = NavType.StringType }
            )
        }
    }
}

private fun NavHostController.navigateToSingleArticle(
    body: String,
    authorName: String,
    authorImageUrl: String,
    articleImageUrl: String,
    title: String
) {
    this.navigateSingleTopTo("${MainActivity.Screen.SingleArticle.route}/$body/$authorName/$authorImageUrl/$articleImageUrl/$title")
}

private fun NavHostController.navigateToSingleLeague(
    articleImageUrl: String,
    title: String
) {
    this.navigateSingleTopTo("${MainActivity.Screen.SingleLeague.route}/$articleImageUrl/$title")
}

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        launchSingleTop = true
    }
}