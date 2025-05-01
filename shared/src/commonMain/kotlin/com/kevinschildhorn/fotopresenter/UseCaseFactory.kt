package com.kevinschildhorn.fotopresenter

import com.kevinschildhorn.fotopresenter.domain.RetrieveDirectoryContentsUseCase
import com.kevinschildhorn.fotopresenter.domain.RetrieveImageDirectoriesUseCase

expect object UseCaseFactory {
    val retrieveImageDirectoriesUseCase: RetrieveImageDirectoriesUseCase
    val retrieveDirectoryContentsUseCase: RetrieveDirectoryContentsUseCase
}
