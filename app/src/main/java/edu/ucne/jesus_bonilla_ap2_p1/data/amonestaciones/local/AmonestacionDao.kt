package edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AmonestacionDao {
    @Upsert
    suspend fun upsert(entity: AmonestacionEntity): Long

    @Delete
    suspend fun delete(entity: AmonestacionEntity)

    @Query("SELECT * FROM amonestaciones ORDER BY amonestacionId DESC")
    fun observeAll(): Flow<List<AmonestacionEntity>>

    @Query("SELECT * FROM amonestaciones WHERE amonestacionId = :id")
    suspend fun getById(id: Int): AmonestacionEntity?

    @Query("DELETE FROM amonestaciones WHERE amonestacionId = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM amonestaciones WHERE amonestacionId = :id)")
    suspend fun exists(id: Int): Boolean
}