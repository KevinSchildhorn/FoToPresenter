package com.kevinschildhorn

import MainView
import android.Manifest
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import co.touchlab.kermit.Logger
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.kevinschildhorn.fotopresenter.baseLogger
import com.kevinschildhorn.fotopresenter.data.network.SMBJHandler
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.startKoin
import com.kevinschildhorn.fotopresenter.ui.ByteArrayFetcher
import com.kevinschildhorn.fotopresenter.ui.SMBJFetcher
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : AppCompatActivity(), KoinComponent {

    private val loginViewModel by viewModel<LoginViewModel>()
    private val directoryViewModel by viewModel<DirectoryViewModel>()
    private val slideshowViewModel by viewModel<SlideshowViewModel>()
    private val playlistViewModel by viewModel<PlaylistViewModel>()
    private val imageRepository: ImageRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin(this)

        setContent {

            setSingletonImageLoaderFactory { context ->
                ImageLoader.Builder(context)
                    .components {
                        add(SMBJFetcher.Factory(imageRepository, baseLogger))
                        add(ByteArrayFetcher.Factory(Logger.withTag("ByteArrayFetcher")))
                    }
                    .memoryCache {
                        MemoryCache.Builder()
                            .maxSizePercent(context,0.25)
                            .build()
                    }
                    .diskCache {
                        DiskCache.Builder()
                            .directory(context.cacheDir.resolve("image_cache"))
                            .maxSizePercent(0.02)
                            .build()
                    }
                    .build()
            }

            MainView(loginViewModel, directoryViewModel, slideshowViewModel, playlistViewModel)
        }
    }
}
