package me.kevinschildhorn.android.ui.gallery

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import me.kevinschildhorn.android.R
import me.kevinschildhorn.android.ui.extensions.asContentScale
import me.kevinschildhorn.common.ui.atomic.atoms.ImageAtom
import me.kevinschildhorn.common.ui.atomic.atoms.gallery.GalleryPhotoAtom
import kotlin.random.Random

@Composable
fun GalleryPhotoItem(bitmap: Bitmap) {
    val atom = GalleryPhotoAtom()

    GalleryItem {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "",
            contentScale = atom.cropStyle.asContentScale
        )
    }
}

@Preview
@Composable
fun GalleryPhotoItemPreview() {
    val drawable = when (Random.nextInt(1, 4)) {
        1 -> R.drawable.sample_photo1
        2 -> R.drawable.sample_photo2
        3 -> R.drawable.sample_photo3
        else -> R.drawable.sample_photo4
    }
    val bitmap =
        BitmapFactory.decodeResource(LocalContext.current.resources, drawable)
    GalleryPhotoItem(bitmap)
}