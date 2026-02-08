package com.nels.master.kmptutorial1.ui.screens.detail
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.nels.master.kmptutorial1.movies
import com.nels.master.kmptutorial1.ui.screens.TemplateScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen() {
    val movie = movies[0]
    TemplateScreen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = movie.title) },
                    navigationIcon = {
                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = movie.title
                            )
                        }
                    }
                )
            },

            ) {}
    }
}