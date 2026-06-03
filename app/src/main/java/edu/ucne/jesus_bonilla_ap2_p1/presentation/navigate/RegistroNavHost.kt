package edu.ucne.jesus_bonilla_ap2_p1.presentation.navigate

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.jesus_bonilla_ap2_p1.presentation.edit.EditBorrameScreen
import edu.ucne.jesus_bonilla_ap2_p1.presentation.list.BorrameListScreen

@Composable
fun RegistroNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding : PaddingValues
){
    NavHost(
        navController = navController,
        startDestination = Screen.BorrameList,
        modifier = Modifier.padding(innerPadding)

    ){
        composable<Screen.BorrameList> {
            BorrameListScreen(
                onAddBorrame = {
                    navController.navigate(Screen.BorrameEdit(0))
                },
                onNavigateToEdit = { id ->
                    navController.navigate(Screen.BorrameEdit(id))
                }
            )
        }

        composable<Screen.BorrameEdit> {
            EditBorrameScreen(
                onBack = {
                    navController.navigate(Screen.BorrameList) {
                        popUpTo(Screen.BorrameList) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }

}