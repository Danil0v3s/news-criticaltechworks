package br.com.firstsoft.feature.news.api.model

import java.time.Instant

data class Article(
    val source: ArticleSource,
    val author: String,
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val publishedAt: Instant,
    val content: String
) {
    data class ArticleSource(
        val id: String,
        val name: String
    )
}
