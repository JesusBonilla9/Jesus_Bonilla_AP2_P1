package edu.ucne.jesus_bonilla_ap2_p1.domain.borrame.repository

import edu.ucne.jesus_bonilla_ap2_p1.domain.borrame.model.Borrame
import kotlinx.coroutines.flow.Flow

interface BorrameRepository {
    fun observeBorrame(): Flow<List<Borrame>>
    suspend fun getBorrame(id: Int): Borrame?
    suspend fun upsert(borrame: Borrame) : Int
    suspend fun delete(id: Int)
    suspend fun exists(id: Int): Boolean
}