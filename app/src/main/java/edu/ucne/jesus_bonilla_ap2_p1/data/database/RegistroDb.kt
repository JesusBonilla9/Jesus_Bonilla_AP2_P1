package edu.ucne.jesus_bonilla_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.local.AmonestacionDao
import edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.local.AmonestacionEntity

@Database(
    entities = [AmonestacionEntity:: class],
    version = 2
)
abstract class RegistroDb: RoomDatabase(){
    abstract fun AmonestacionDao(): AmonestacionDao
}