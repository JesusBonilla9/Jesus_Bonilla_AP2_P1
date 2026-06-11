package edu.ucne.jesus_bonilla_ap2_p1.presentation.edit


data class EditAmonestacionUiState(
    val amonestacionId: Int? = null,
    val nombre: String = "",
    val razon: String = "",
    val monto: String = "",
    val nombreError: String? = null,
    val razonError: String? = null,
    val montoError: String? = null,
    val isSaving: Boolean = false,
    val isDeleting: Boolean = false,
    val isNew: Boolean = true,
    val saved: Boolean = false,
    val deleted: Boolean = false
)