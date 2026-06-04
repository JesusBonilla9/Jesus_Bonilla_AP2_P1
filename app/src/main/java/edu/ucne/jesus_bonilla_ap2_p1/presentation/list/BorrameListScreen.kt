package edu.ucne.jesus_bonilla_ap2_p1.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BorrameListScreen(
    onAddBorrame: () -> Unit,
    onNavigateToEdit: (Int) -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddBorrame) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Borrame")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(text = "Lista de registros")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BorrameListScreenPreview() {
    BorrameListScreen(
        onAddBorrame = {},
        onNavigateToEdit = {}
    )
}