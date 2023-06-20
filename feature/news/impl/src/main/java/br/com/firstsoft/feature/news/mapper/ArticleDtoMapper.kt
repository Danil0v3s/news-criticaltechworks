package br.com.firstsoft.feature.news.mapper

import br.com.firstsoft.core.network.api.dto.NewsResponseDto
import br.com.firstsoft.feature.news.api.model.Article

internal fun NewsResponseDto.toDomain(): List<Article> {
    return articles.map {
        Article(
            source = Article.ArticleSource(id = it.source.id, name = it.source.name),
            author = it.author,
            title = it.title,
            description = it.description,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content
        )
    }
}
