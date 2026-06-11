package edu.ucne.jesus_bonilla_ap2_p1.presentation.navigate

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.jesus_bonilla_ap2_p1.presentation.edit.EditAmonestacionScreen
import edu.ucne.jesus_bonilla_ap2_p1.presentation.list.AmonestacionListScreen

@Composable
fun RegistroNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding : PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = Screen.AmonestacionList,
        modifier = Modifier.padding(innerPadding)

    ){
        composable<Screen.AmonestacionList> {
            AmonestacionListScreen(
                onAddAmonestacion = {
                    navController.navigate(Screen.AmonestacionEdit(0))
                },
                onNavigateToEdit = { id ->
                    navController.navigate(Screen.AmonestacionEdit(id))
                }
            )
        }

        composable<Screen.AmonestacionEdit> {
            EditAmonestacionScreen(
                onBack = {
                    navController.navigate(Screen.AmonestacionList) {
                        popUpTo(Screen.AmonestacionList) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }

}