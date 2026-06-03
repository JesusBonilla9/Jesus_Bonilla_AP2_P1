package edu.ucne.jesus_bonilla_ap2_p1.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.jesus_bonilla_ap2_p1.data.borrame.local.BorrameDao
import edu.ucne.jesus_bonilla_ap2_p1.data.database.RegistroDb
import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent:: class)
    object AppModule {
        @Provides
        @Singleton
        fun provideRegistroDbDataBase(
            @ApplicationContext context: Context
        ): RegistroDb{
            return Room.databaseBuilder(context, RegistroDb :: class.java, "registro_db")
                .fallbackToDestructiveMigration()
                .build()


        }
        @Provides
        @Singleton
        fun provideBorrameDao(database: RegistroDb): BorrameDao {
            return database.BorrameDao()
        }
    }
