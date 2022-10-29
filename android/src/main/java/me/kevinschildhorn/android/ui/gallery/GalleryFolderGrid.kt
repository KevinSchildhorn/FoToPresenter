package me.kevinschildhorn.android.ui.gallery

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kevinschildhorn.android.R

/*
@Composable
fun GalleryFolderGrid(images: List<Bitmap>) {
    val atom = GalleryPhotoAtom()

    Column {
        val rowHeight = if(images.count() > 2) 0.5f else 1f
        Row(
            Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(rowHeight)
        ) {
            val width = if(images.count() > 1) 0.5f else 1f
            GridImage(images[0], width)
            images.getOrNull(1)?.let {
                GridImage(it, 1f)
            }
        }
        Row(
            Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f)
        ) {
            images.getOrNull(2)?.let {
                val width = if(images.count() > 3) 0.5f else 1f
                GridImage(it, width)
            }
            images.getOrNull(3)?.let {
                GridImage(it, 1f)
            }
        }
    }
}

@Composable
private fun GridImage(image: Bitmap, width: Float) {
    Image(
        modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(width),
        bitmap = image.asImageBitmap(),
        contentScale = ContentScale.Crop,
        contentDescription = ""
    )
}

@Preview
@Composable
fun GalleryFolderGridPreview() {
    val bitmap1 =
        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo1)
    val bitmap2 =
        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo2)
    val bitmap3 =
        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo3)
    val bitmap4 =
        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo4)

    Column {

        Box(
            Modifier
                .height(100.dp)
                .width(100.dp)
        ) {
            GalleryFolderGrid(
                listOf(
                    bitmap1,
                )
            )
        }
        Box(
            Modifier
                .height(100.dp)
                .width(100.dp)
        ) {
            GalleryFolderGrid(
                listOf(
                    bitmap1,
                    bitmap2,
                )
            )
        }
        Box(
            Modifier
                .height(100.dp)
                .width(100.dp)
        ) {
            GalleryFolderGrid(
                listOf(
                    bitmap1,
                    bitmap2,
                    bitmap3
                )
            )
        }
        Box(
            Modifier
                .height(100.dp)
                .width(100.dp)
        ) {
            GalleryFolderGrid(
                listOf(
                    bitmap1,
                    bitmap2,
                    bitmap3,
                    bitmap4
                )
            )
        }
    }
}*/