package edu.ucne.jesus_bonilla_ap2_p1.presentation.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAmonestacionScreen(
    viewModel: EditAmonestacionViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.saved, state.deleted) {
        if (state.saved || state.deleted) {
            onBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.isNew) "Nueva Amonestacion" else "Editar Amonestacion") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Atras")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.nombre,
                onValueChange = { viewModel.onEvent(EditAmonestacionUiEvent.NombreChanged(it)) },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth().testTag("input_nombre"),
                isError = state.nombreError != null,
                supportingText = state.nombreError?.let { { Text(it) } },
                singleLine = true
            )

            OutlinedTextField(
                value = state.razon,
                onValueChange = {viewModel.onEvent(EditAmonestacionUiEvent.RazonChanged(it)) },
                label = { Text("Razon") },
                modifier = Modifier.fillMaxWidth().testTag("input_razon"),
                isError = state.razonError != null,
                supportingText = state.razonError?.let { { Text(it) } },
                singleLine = true
            )


            OutlinedTextField(
                value = state.monto,
                onValueChange = { viewModel.onEvent(EditAmonestacionUiEvent.MontoChanged(it)) },
                label = { Text("Monto") },
                modifier = Modifier.fillMaxWidth().testTag("input_monto"),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                isError = state.montoError != null,
                supportingText = state.montoError?.let { { Text(it) } },
                singleLine = true
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (!state.isNew) {
                    Button(
                        onClick = { viewModel.onEvent(EditAmonestacionUiEvent.Delete) },
                        modifier = Modifier.weight(1f).testTag("btn_delete"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ),
                        enabled = !state.isDeleting
                    ) {
                        if (state.isDeleting) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                        } else {
                            Icon(Icons.Default.Delete, contentDescription = null)
                            Spacer(Modifier.width(4.dp))
                            Text("Eliminar")
                        }
                    }
                }

                Button(
                    onClick = { viewModel.onEvent(EditAmonestacionUiEvent.Save) },
                    modifier = Modifier.weight(1f).testTag("btn_save"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    ),
                    enabled = !state.isSaving
                ) {
                    if (state.isSaving) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                    } else {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EditBorrameBodyPreview() {
    EditAmonestacionScreen(
        onBack = {}
    )
}