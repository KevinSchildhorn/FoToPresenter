package me.kevinschildhorn.android.ui.gallery

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import me.kevinschildhorn.android.R
import me.kevinschildhorn.common.ui.atomic.atoms.ImageAtom
import me.kevinschildhorn.common.ui.atomic.organisms.gallery.GalleryFolderOrganism

@Composable
fun GalleryFolderItem(name: String, images: List<Bitmap>) {
    val atom = GalleryFolderOrganism(name, images.map { ImageAtom(it) })

    atom.atoms
    GalleryItem {
        Column(
            Modifier.fillMaxHeight()
        ) {
            Row(Modifier.fillMaxHeight(0.75f)) {
                GalleryFolderGrid(images)
            }
            Box(
                Modifier.fillMaxSize(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name,
                    fontSize = 30.sp,
                )
            }
        }
    }
}

@Preview
@Composable
fun GalleryFolderItemPreview() {
    val bitmap1 =
        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo1)
    val bitmap2 =
        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo2)
    val bitmap3 =
        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo3)
    val bitmap4 =
        BitmapFactory.decodeResource(LocalContext.current.resources, R.drawable.sample_photo4)
    GalleryFolderItem("Hello", listOf(
        bitmap1,
        bitmap2,
        bitmap3,
        bitmap4
    ))
}