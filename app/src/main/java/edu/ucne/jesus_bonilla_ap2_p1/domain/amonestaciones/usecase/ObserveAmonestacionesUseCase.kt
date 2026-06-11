package edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.usecase

import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.model.Amonestacion
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAmonestacionesUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    operator fun invoke(): Flow<List<Amonestacion>> = repository.observeAmonestaciones()
}