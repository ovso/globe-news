package io.github.ovso.globenews.core.database

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.ovso.globenews.core.database.dao.ArticleDao

@Suppress("unused", "SpellCheckingInspection")
@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesArticleDao(
        database: AppDatabase,
    ): ArticleDao = database.articleDao()

}