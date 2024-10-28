package com.fransiska.drakor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fransiska.drakor.data.NewsDatasource
import com.fransiska.drakor.model.NewsArticle
import com.fransiska.drakor.ui.theme.DrakorTheme
// kelas utama
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrakorTheme  {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BeritaApp()
                }
            }
        }
    }
}

@Composable
fun BeritaApp() {
    NewsList(
        newsList = NewsDatasource().loadNewsArticles(),
    )
}

@Composable
fun NewsList(newsList: List<NewsArticle>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(newsList) { article ->
            NewsCard(
                article = article,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun NewsCard(article: NewsArticle, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(article.imageResourceId),
                contentDescription = stringResource(article.titleResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                    modifier = Modifier.padding(16.dp)
                    ) {
                Text(
                    text = LocalContext.current.getString(article.titleResourceId),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = LocalContext.current.getString(article.bgnResourceId),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsCardPreview() {
    NewsCard(NewsArticle(R.string.film_title_1, R.string.film_description_1,R.drawable.image1))
}