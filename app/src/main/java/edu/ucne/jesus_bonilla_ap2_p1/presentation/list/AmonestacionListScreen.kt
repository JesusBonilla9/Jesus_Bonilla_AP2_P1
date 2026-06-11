package edu.ucne.jesus_bonilla_ap2_p1.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.model.Amonestacion

@Composable
fun AmonestacionListScreen(
    viewModel: AmonestacionListViewModel = hiltViewModel(),
    onAddAmonestacion: () -> Unit,
    onNavigateToEdit: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.navigateToCreate) {
        if (state.navigateToCreate) {
            onAddAmonestacion()
        }
    }

    LaunchedEffect(state.navigateToEditId) {
        state.navigateToEditId?.let { id ->
            onNavigateToEdit(id)
        }
    }

    AmonestacionListBody(state, viewModel::onEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmonestacionListBody(
    state: AmonestacionListUiState,
    onEvent: (AmonestacionListUiEvent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.message) {
        state.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(AmonestacionListUiEvent.ClearMessage)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { TopAppBar(title = { Text("Lista de Amonestaciones") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(AmonestacionListUiEvent.CreateNew) },
                modifier = Modifier.testTag("fab_add")
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Amonestacion")
            }
        },
        bottomBar = {
            BottomAppBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {
                Text(
                    text = "Total Balance: RD$ ${"%.2f".format(state.totalMonto)}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = state.filterQuery,
                onValueChange = { onEvent(AmonestacionListUiEvent.FilterChanged(it)) },
                label = { Text("Buscar por nombre...") },
                modifier = Modifier.fillMaxWidth().padding(16.dp).testTag("input_buscar")
            )

            Box(modifier = Modifier.fillMaxSize()) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center).testTag("loading")
                    )
                } else {
                    if (state.amonestacionesFiltradas.isEmpty()) {
                        Text(
                            text = "No hay amonestaciones registradas",
                            modifier = Modifier.align(Alignment.Center).testTag("empty_message"),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(
                                items = state.amonestacionesFiltradas,
                                key = { it.amonestacionId }
                            ) { amonestacion ->
                                BancoItem(
                                    amonestacion = amonestacion,
                                    onClick = { onEvent(AmonestacionListUiEvent.Edit(amonestacion.amonestacionId)) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BancoItem(
    amonestacion: Amonestacion,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("amonestacion_item_${amonestacion.amonestacionId}")
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = amonestacion.nombres,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Razon: ${amonestacion.razon}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Monto: RD$ ${amonestacion.monto}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BorrameListScreenPreview() {
    AmonestacionListScreen(
        onAddAmonestacion = {},
        onNavigateToEdit = {}
    )
}