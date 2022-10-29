package me.kevinschildhorn.android.ui.gallery
/*
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.kevinschildhorn.android.R
import me.kevinschildhorn.common.ui.atomic.templates.GalleryGridTemplate

@Composable
fun GalleryGrid(folders: List<Bitmap>, photos: List<Bitmap>) {
    val galleryGridOrganism = GalleryGridTemplate()
    LazyVerticalGrid(
        columns = GridCells.Fixed(galleryGridOrganism.defaultColumns),
        verticalArrangement = Arrangement.spacedBy(galleryGridOrganism.padding.dp),
        horizontalArrangement = Arrangement.spacedBy(galleryGridOrganism.padding.dp)
    ) {
        items(folders) { folder ->
            GalleryFolderItem(name = "Hello", images = photos)
        }
        items(photos) { photo ->
            GalleryPhotoItem(bitmap = photo)
        }
    }
}

@Preview
@Composable
fun GalleryGridPreview() {
    GalleryGrid(
        folders = listOf(
            BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo3),
        ),
        photos = listOf(
            BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo1),
            BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo2),
            BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo3),
            BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo4),
            BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo4),
            BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo3),
            BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo2),
            BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo1),
        )
    )
}*/