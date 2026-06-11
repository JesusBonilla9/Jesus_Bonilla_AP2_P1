package edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "amonestaciones")
data class AmonestacionEntity(
    @PrimaryKey(autoGenerate = true)
    val amonestacionId: Int = 0,
    val nombres : String = "",
    val razon: String = "",
    val monto : Double = 0.0
)
