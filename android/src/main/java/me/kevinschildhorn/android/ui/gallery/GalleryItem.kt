package me.kevinschildhorn.android.ui.gallery

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.kevinschildhorn.android.ui.extensions.fromAtom
import me.kevinschildhorn.common.ui.atomic.atoms.gallery.GalleryItemAtom

@Composable
fun GalleryItemAtom.asComposable(content:@Composable() () -> Unit = {}){
    Box(
        modifier = Modifier.fromAtom(this),
    ) {
        content()
    }
}

@Preview
@Composable
fun GalleryItemPreview() {
    GalleryItemAtom().asComposable()
}