package br.com.firstsoft.core.network.api.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.Instant

@JsonClass(generateAdapter = true)
data class NewsResponseDto(
    @Json(name = "status") val status: String,
    @Json(name = "code") val code: String?,
    @Json(name = "message") val message: String?,
    @Json(name = "totalResults") val totalResults: Int,
    @Json(name = "articles") val articles: List<ArticleDto>
) {
    @JsonClass(generateAdapter = true)
    data class ArticleDto(
        @Json(name = "source") val source: ArticleSourceDto,
        @Json(name = "author") val author: String,
        @Json(name = "title") val title: String,
        @Json(name = "description") val description: String?,
        @Json(name = "urlToImage") val urlToImage: String?,
        @Json(name = "publishedAt") val publishedAt: Instant,
        @Json(name = "content") val content: String
    ) {
        @JsonClass(generateAdapter = true)
        data class ArticleSourceDto(
            @Json(name = "id") val id: String,
            @Json(name = "name") val name: String
        )
    }
}
