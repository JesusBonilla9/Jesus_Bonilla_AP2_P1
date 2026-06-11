package edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.usecase

import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.model.Amonestacion
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.repository.AmonestacionRepository
import javax.inject.Inject

class UpsertAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    suspend operator fun invoke(amonestacion: Amonestacion): Result<Int> {
        val nombreResult = validateNombres(amonestacion.nombres)
        if (!nombreResult.isValid) return Result.failure(IllegalArgumentException("nombre:${nombreResult.error}"))

        val montoResult = validateMonto(amonestacion.monto.toString())
        if (!montoResult.isValid) return Result.failure(IllegalArgumentException("monto:${montoResult.error}"))

        val razonResult = validateRazon(amonestacion.razon)
        if (!razonResult.isValid) return Result.failure(IllegalArgumentException("razon:${razonResult.error}"))

        return runCatching { repository.upsert(amonestacion) }
    }
}