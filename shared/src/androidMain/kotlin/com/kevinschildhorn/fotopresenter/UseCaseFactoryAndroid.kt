@file:Suppress("ktlint:standard:filename")

package com.kevinschildhorn.fotopresenter

import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.RetrieveImageDirectoriesUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual object UseCaseFactory : KoinComponent {
    actual val retrieveImageDirectoriesUseCase: RetrieveImageDirectoriesUseCase
        get() {
            val useCase: RetrieveImageDirectoriesUseCase by inject()
            return useCase
        }
    actual val retrieveDirectoryContentsUseCase: RetrieveDirectoryContentsUseCase
        get() {
            val useCase: RetrieveDirectoryContentsUseCase by inject()
            return useCase
        }
}
