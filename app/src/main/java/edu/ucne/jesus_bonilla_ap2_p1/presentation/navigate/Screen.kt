package edu.ucne.jesus_bonilla_ap2_p1.presentation.navigate

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object AmonestacionList: Screen()
    @Serializable
    data class AmonestacionEdit(val amonestacionId : Int = 0) : Screen()

}