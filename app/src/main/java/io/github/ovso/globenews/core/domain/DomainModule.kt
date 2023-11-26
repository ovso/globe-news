package io.github.ovso.globenews.core.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {
    @Binds
    fun bindsGetNewsUseCase(useCase: GetNewsUseCaseImpl): GetNewsUseCase

}
