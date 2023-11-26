package io.github.ovso.globenews.core.utils

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
interface UtilsModule {
    @Binds
    fun bindsNetworkUtils(utils: NetworkUtilsImpl): NetworkUtils

}
