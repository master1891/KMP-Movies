package com.nels.master.kmptutorial1.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.nels.master.kmptutorial1.data.Movie
import com.nels.master.kmptutorial1.ui.common.ProgressIndicator
import com.nels.master.kmptutorial1.ui.screens.TemplateScreen
import kmptutorial1.composeapp.generated.resources.Res
import kmptutorial1.composeapp.generated.resources.go_back
import kmptutorial1.composeapp.generated.resources.original_language
import kmptutorial1.composeapp.generated.resources.original_title
import kmptutorial1.composeapp.generated.resources.popularity
import kmptutorial1.composeapp.generated.resources.release_date
import kmptutorial1.composeapp.generated.resources.vote_average
import kmptutorial1.composeapp.generated.resources.vote_count
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit) {

    val state = vm.uiState.value
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    TemplateScreen {
        Scaffold(
            topBar = {
                DetailTopAppBar(state.movie?.title ?: "Cargando ...", onBack, scrollBehavior)
            },
        ) { padding ->
            ProgressIndicator(state.loading, modifier = Modifier.padding(padding))
            state.movie?.let { movie ->
                Detail(Modifier.padding(padding), movie)
            }
        }
    }
}

@Composable
private fun Detail(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = movie.backdrop?: movie.postr,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        )

        Text(
            text = movie.overview,
            modifier = Modifier.padding(16.dp),
            maxLines = 1
        )
        Text(
            text = buildAnnotatedString {
                property(stringResource(Res.string.original_language), movie.originalLanguage)
                property(stringResource(Res.string.original_title), movie.originalTitle)
                property(stringResource(Res.string.popularity), movie.popularity.toString())
                property(stringResource(Res.string.release_date), movie.releaseDate)
                property(stringResource(Res.string.vote_average), movie.voteAverage.toString())
                property(stringResource(Res.string.vote_count), movie.voteAverage.toString(), isLast = true)
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp)
        )
    }
}

private fun AnnotatedString.Builder.property(name: String, value: String, isLast: Boolean = false) {
    withStyle(
        ParagraphStyle(lineHeight = 18.sp)
    ) {

        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append("$name: ")
        }
        append(value)
        if (!isLast)
            append("\n")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopAppBar(
    title: String,
    onBack: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.go_back)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}
