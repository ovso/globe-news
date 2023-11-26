package io.github.ovso.globenews.core.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsNewsRepository(
        newsRepository: NewsRepositoryImpl,
    ): NewsRepository
    @Binds
    fun bindsTopHeadlinesDataSource(
        dataSource: TopHeadlinesDataSourceImpl,
    ): TopHeadlinesDataSource

}