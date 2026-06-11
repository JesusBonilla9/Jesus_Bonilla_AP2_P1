package edu.ucne.jesus_bonilla_ap2_p1.presentation.list

import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.model.Amonestacion

data class AmonestacionListUiState(
    val isLoading: Boolean = false,
    val amonestaciones: List<Amonestacion> = emptyList(),
    val amonestacionesFiltradas: List<Amonestacion> = emptyList(),
    val totalMonto: Double = 0.0,
    val filterQuery: String = "",
    val message: String? = null,
    val navigateToCreate: Boolean = false,
    val navigateToEditId: Int? = null,
    val error: String? = null
)