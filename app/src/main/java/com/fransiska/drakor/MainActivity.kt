package com.fransiska.drakor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fransiska.drakor.data.DramaDatasource
import com.fransiska.drakor.model.Drama
import com.fransiska.drakor.ui.theme.DrakorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrakorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DramaApp()
                }
            }
        }
    }
}

@Composable
fun DramaApp() {
    Row(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            Text("Menampilkan Rekomendasi Drama Terpopuler", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
            PopularDramaList(dramaList = DramaDatasource.loadPopularDramas())
        }
        Column(modifier = Modifier.weight(1f)) {
            Text("Drama Terfavorit", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
            FavoriteDramaList(dramaList = DramaDatasource.loadPopularDramas()) // If favorites are the same, consider changing this
        }
    }
}

@Composable
fun PopularDramaList(dramaList: List<Drama>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(dramaList) { drama ->
            DramaCard(drama = drama, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun FavoriteDramaList(dramaList: List<Drama>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(dramaList) { drama ->
            DramaCard(drama = drama, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DramaCard(drama: Drama, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(drama.imageResourceId),
                contentDescription = stringResource(drama.titleResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = LocalContext.current.getString(drama.titleResourceId),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = LocalContext.current.getString(drama.descriptionResourceId),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DramaCardPreview() {
    DramaCard(Drama(R.string.film_title_1, R.string.film_description_1, R.drawable.image1))
}