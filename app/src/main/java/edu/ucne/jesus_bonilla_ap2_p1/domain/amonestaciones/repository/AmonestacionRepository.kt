package edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.repository

import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.model.Amonestacion
import kotlinx.coroutines.flow.Flow

interface AmonestacionRepository {
    fun observeAmonestaciones(): Flow<List<Amonestacion>>
    suspend fun getAmonestacion(id: Int): Amonestacion?
    suspend fun upsert(amonestacion: Amonestacion) : Int
    suspend fun delete(id: Int)
    suspend fun exists(id: Int): Boolean
}