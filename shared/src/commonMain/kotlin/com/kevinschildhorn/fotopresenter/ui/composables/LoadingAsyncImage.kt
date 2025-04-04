package com.kevinschildhorn.fotopresenter.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.kevinschildhorn.fotopresenter.Res
import com.kevinschildhorn.fotopresenter.error
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.shimmer
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoadingAsyncImage(
    model: Any?,
    contentDescription: String?,
    contentScale: ContentScale,
    modifier: Modifier = Modifier,
) {
    var loading by remember { mutableStateOf(true) }
    AsyncImage(
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier,
        onLoading = { loading = true },
        onSuccess = { loading = false },
        onError = { loading = false },
        error = painterResource(Res.drawable.error),
    )
    if (loading) {
        val shimmerTheme = defaultShimmerTheme.copy(rotation = 35f)
        CompositionLocalProvider(LocalShimmerTheme provides shimmerTheme) {
            Box(
                modifier = Modifier.fillMaxSize().shimmer(),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier =
                        Modifier.fillMaxSize()
                            .background(MaterialTheme.colors.primaryVariant),
                )
            }
        }
    }
}
