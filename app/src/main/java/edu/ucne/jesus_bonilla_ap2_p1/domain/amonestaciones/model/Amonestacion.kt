package edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.model

data class Amonestacion(
    val amonestacionId: Int = 0,
    val nombres: String = "",
    val razon: String = "",
    val monto: Double = 0.0
)