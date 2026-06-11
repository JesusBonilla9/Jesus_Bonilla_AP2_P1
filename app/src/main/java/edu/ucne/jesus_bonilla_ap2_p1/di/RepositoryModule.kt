package edu.ucne.jesus_bonilla_ap2_p1.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.jesus_bonilla_ap2_p1.data.amonestaciones.repository.AmonestacionRepositoryImpl
import edu.ucne.jesus_bonilla_ap2_p1.domain.amonestaciones.repository.AmonestacionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAmonestacionRepository(
        impl: AmonestacionRepositoryImpl
    ): AmonestacionRepository

}