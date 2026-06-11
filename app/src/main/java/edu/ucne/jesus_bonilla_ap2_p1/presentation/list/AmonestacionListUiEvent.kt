package edu.ucne.jesus_bonilla_ap2_p1.presentation.list

sealed interface AmonestacionListUiEvent {
    data object Load : AmonestacionListUiEvent
    data object Refresh : AmonestacionListUiEvent
    data class Delete(val id: Int) : AmonestacionListUiEvent
    data class ShowMessage(val message: String) : AmonestacionListUiEvent
    data object ClearMessage : AmonestacionListUiEvent
    data object CreateNew : AmonestacionListUiEvent
    data class Edit(val id: Int) : AmonestacionListUiEvent
    data class FilterChanged(val query: String) : AmonestacionListUiEvent
}