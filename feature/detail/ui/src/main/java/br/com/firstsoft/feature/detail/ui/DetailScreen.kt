package br.com.firstsoft.feature.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import br.com.firstsoft.feature.news.api.model.Article
import coil.compose.AsyncImage

@Composable
fun DetailScreen(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = article.urlToImage,
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = article.title, style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = article.author, style = MaterialTheme.typography.body2)
                Text(text = article.publishedAt.toString(), style = MaterialTheme.typography.body2)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = article.content, style = MaterialTheme.typography.body1)
        }
    }
}
