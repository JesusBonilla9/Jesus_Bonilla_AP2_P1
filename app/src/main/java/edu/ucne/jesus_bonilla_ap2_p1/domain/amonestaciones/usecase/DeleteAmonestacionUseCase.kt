package edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.usecase

import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.repository.AmonestacionRepository
import javax.inject.Inject

class DeleteAmonestacionUseCase @Inject constructor(
    private val repository: AmonestacionRepository
) {
    suspend operator fun invoke(id: Int) = repository.delete(id)
}