package io.github.ovso.globenews.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "published_at") val publishedAt: String? = null,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "url") val url: String? = null,
    @ColumnInfo(name = "url_to_image") val urlToImage: String? = null,
    @ColumnInfo(name = "viewed") val viewed: Boolean = false,
)