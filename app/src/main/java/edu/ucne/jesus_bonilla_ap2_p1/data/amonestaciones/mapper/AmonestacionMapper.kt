package edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.mapper

import edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.local.AmonestacionEntity
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.model.Amonestacion

fun AmonestacionEntity.toDomain() = Amonestacion(
    amonestacionId = amonestacionId,
    nombres = nombres,
    razon = razon,
    monto = monto,

)

fun Amonestacion.toEntity() = AmonestacionEntity(
    amonestacionId = amonestacionId,
    nombres = nombres,
    razon = razon,
    monto = monto
)

