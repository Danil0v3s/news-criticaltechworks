package br.com.firstsoft.feature.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.firstsoft.feature.news.api.model.Article

@Composable
fun DetailScreen(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
       Text(text = article.title, style = MaterialTheme.typography.h1)
    }
}