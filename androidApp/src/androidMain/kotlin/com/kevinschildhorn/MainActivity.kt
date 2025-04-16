package com.kevinschildhorn

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import co.touchlab.kermit.Logger
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.kevinschildhorn.fotopresenter.data.repositories.ImageRepository
import com.kevinschildhorn.fotopresenter.extension.logLargeTitle
import com.kevinschildhorn.fotopresenter.startKoin
import com.kevinschildhorn.fotopresenter.ui.SharedImageFetcher
import com.kevinschildhorn.fotopresenter.ui.SMBJFetcher
import com.kevinschildhorn.fotopresenter.ui.screens.directory.DirectoryViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.login.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.playlist.PlaylistViewModel
import com.kevinschildhorn.fotopresenter.ui.screens.slideshow.SlideshowViewModel
import okhttp3.OkHttpClient
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
        val logger = Logger.withTag("MainActivity")
        logger.logLargeTitle("App has started")

        setContent {

            setSingletonImageLoaderFactory { context ->
                ImageLoader.Builder(context)
                    .components {
                        add(SMBJFetcher.Factory(imageRepository, Logger))
                        //add(SharedImageFetcher.Factory(Logger))
                        add(
                            OkHttpNetworkFetcherFactory(
                                callFactory = {
                                    OkHttpClient()
                                }
                            )
                        )
                    }
                    .memoryCache {
                        MemoryCache.Builder()
                            .maxSizePercent(context, 0.25)
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
