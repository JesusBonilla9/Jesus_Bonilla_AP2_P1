package edu.ucne.jesus_bonilla_ap2_p1.presentation.navigate

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object BorrameList: Screen()
    @Serializable
    data class BorrameEdit(val borrameId : Int = 0) : Screen()

}