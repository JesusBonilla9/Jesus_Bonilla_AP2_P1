package edu.ucne.jesus_bonilla_ap2_p1.presentation.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.usecase.DeleteAmonestacionUseCase
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.usecase.ObserveAmonestacionesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmonestacionListViewModel @Inject constructor(
    private val observeAmonestacionesUseCase: ObserveAmonestacionesUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(AmonestacionListUiState(isLoading = true))
    val state: StateFlow<AmonestacionListUiState> = _state.asStateFlow()

    init {
        loadAmonestaciones()
    }

    fun onEvent(event: AmonestacionListUiEvent) {
        when (event) {
            AmonestacionListUiEvent.Load, AmonestacionListUiEvent.Refresh -> loadAmonestaciones()
            is AmonestacionListUiEvent.FilterChanged -> {
                val query = event.query
                val filtrados = if (query.isBlank()) _state.value.amonestaciones else _state.value.amonestaciones.filter { it.nombres.contains(query, ignoreCase = true) }
                _state.update {
                    it.copy(filterQuery = query, amonestacionesFiltradas = filtrados, totalMonto = filtrados.sumOf{ m -> m.monto })
                }
            }
            is AmonestacionListUiEvent.Delete -> onDelete(event.id)
            is AmonestacionListUiEvent.ShowMessage -> _state.update { it.copy(message = event.message) }
            AmonestacionListUiEvent.ClearMessage -> _state.update { it.copy(message = null) }
            AmonestacionListUiEvent.CreateNew -> _state.update { it.copy(navigateToCreate = true) }
            is AmonestacionListUiEvent.Edit -> _state.update { it.copy(navigateToEditId = event.id) }
        }
    }

    fun loadAmonestaciones() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            observeAmonestacionesUseCase().collectLatest { amonestaciones ->
                val query = _state.value.filterQuery
                val filtrados = if (query.isBlank()) amonestaciones else amonestaciones.filter { it.nombres.contains(query, ignoreCase = true) }
                val total = filtrados.sumOf { it.monto }

                _state.update {
                    it.copy(
                        isLoading = false,
                        amonestaciones = amonestaciones,
                        amonestacionesFiltradas = filtrados,
                        totalMonto = total,
                        message = null
                    )
                }
            }
        }
    }

    private fun onDelete(id: Int) {
        viewModelScope.launch {
            deleteAmonestacionUseCase(id)
            onEvent(AmonestacionListUiEvent.ShowMessage("Amonestacion eliminada"))
        }
    }
}
