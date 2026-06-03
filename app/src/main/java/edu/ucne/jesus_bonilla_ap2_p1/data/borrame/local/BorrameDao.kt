package edu.ucne.jesus_bonilla_ap2_p1.data.borrame.local

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface BorrameDao {
    @Upsert
    suspend fun upsert(entity: BorrameEntity): Long
}