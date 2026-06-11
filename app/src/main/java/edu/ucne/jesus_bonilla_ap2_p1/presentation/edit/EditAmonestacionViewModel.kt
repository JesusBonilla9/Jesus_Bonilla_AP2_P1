package edu.ucne.jesus_bonilla_ap2_p1.presentation.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.model.Amonestacion
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.usecase.DeleteAmonestacionUseCase
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.usecase.GetAmonestacionUseCase
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.usecase.UpsertAmonestacionUseCase
import edu.ucne.jesus_bonilla_ap2_p1.presentation.navigate.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAmonestacionViewModel @Inject constructor(
    private val getAmonestacionUseCase: GetAmonestacionUseCase,
    private val upsertAmonestacionUseCase: UpsertAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val routeArgs = savedStateHandle.toRoute<Screen.AmonestacionEdit>()
    private val amonestacionId: Int = routeArgs.amonestacionId

    private val _state = MutableStateFlow(EditAmonestacionUiState())
    val state: StateFlow<EditAmonestacionUiState> = _state.asStateFlow()

    init {
        loadAmonestacion(amonestacionId)
    }

    fun onEvent(event: EditAmonestacionUiEvent) {
        when (event) {
            is EditAmonestacionUiEvent.Load -> loadAmonestacion(event.id)
            is EditAmonestacionUiEvent.NombreChanged -> _state.update { it.copy(nombre = event.value, nombreError = null) }
            is EditAmonestacionUiEvent.RazonChanged -> _state.update { it.copy(razon = event.value, razonError = null) }
            is EditAmonestacionUiEvent.MontoChanged -> _state.update { it.copy(monto = event.value, montoError = null) }
            EditAmonestacionUiEvent.Save -> onSave()
            EditAmonestacionUiEvent.Delete -> onDelete()
        }
    }

    private fun loadAmonestacion(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, amonestacionId = null) }
            return
        }

        viewModelScope.launch {
            getAmonestacionUseCase(id)?.let { amonestacion ->
                _state.update {
                    it.copy(
                        isNew = false,
                        amonestacionId = amonestacion.amonestacionId,
                        nombre = amonestacion.nombres,
                        razon = amonestacion.razon,
                        monto = amonestacion.monto.toString()
                    )
                }
            } ?: _state.update { it.copy(isNew = true, amonestacionId = null) }
        }
    }

    private fun onSave() {
        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val amonestacion = Amonestacion(
                amonestacionId = state.value.amonestacionId ?: 0,
                nombres = state.value.nombre,
                razon = state.value.razon,
                monto = state.value.monto.toDoubleOrNull() ?: 0.0
            )

            val result = upsertAmonestacionUseCase(amonestacion)
            result.onSuccess { newId ->
                _state.update { it.copy(isSaving = false, saved = true, amonestacionId = newId, isNew = false) }
            }.onFailure { e ->
                val msg = e.message ?: ""
                _state.update {
                    it.copy(
                        isSaving = false,
                        nombreError = if (msg.contains("nombre:")) msg.substringAfter("nombre:") else null,
                        razonError = if (msg.contains("razon:")) msg.substringAfter("razon:") else null,
                        montoError = if (msg.contains("monto:")) msg.substringAfter("monto:") else null
                    )
                }
            }
        }
    }

    private fun onDelete() {
        val id = state.value.amonestacionId ?: return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            deleteAmonestacionUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }
}