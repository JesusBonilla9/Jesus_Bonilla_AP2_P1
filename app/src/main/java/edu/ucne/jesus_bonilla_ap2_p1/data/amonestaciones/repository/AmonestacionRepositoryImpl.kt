package edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.repository

import edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.local.AmonestacionDao
import edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.mapper.toDomain
import edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.mapper.toEntity
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.model.Amonestacion
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AmonestacionRepositoryImpl @Inject constructor(
    private val localDataSource: AmonestacionDao
) : AmonestacionRepository {

    override fun observeAmonestaciones(): Flow<List<Amonestacion>> {
        return localDataSource.observeAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun getAmonestacion(id: Int): Amonestacion? {
        return localDataSource.getById(id)?.toDomain()
    }

    override suspend fun upsert(amonestacion: Amonestacion): Int {
        localDataSource.upsert(amonestacion.toEntity())
        return amonestacion.amonestacionId
    }

    override suspend fun delete(id: Int) {
        localDataSource.deleteById(id)
    }

    override suspend fun exists(id: Int): Boolean {
        return localDataSource.exists(id)
    }
}