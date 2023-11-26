package io.github.ovso.globenews.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.ovso.globenews.core.database.dao.ArticleDao
import io.github.ovso.globenews.core.database.model.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}