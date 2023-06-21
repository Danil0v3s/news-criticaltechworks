package br.com.firstsoft.feature.news.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.Instant

@Parcelize
data class Article(
    val source: ArticleSource,
    val author: String,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: Instant,
    val content: String
) : Parcelable {

    @Parcelize
    data class ArticleSource(
        val id: String,
        val name: String
    ) : Parcelable
}
