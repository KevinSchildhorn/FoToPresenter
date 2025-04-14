package com.kevinschildhorn.fotopresenter.ui.screens.directory

import com.kevinschildhorn.fotopresenter.data.Directory
import com.kevinschildhorn.fotopresenter.data.ImageSlideshowDetails
import com.kevinschildhorn.fotopresenter.data.MetadataFileDetails
import com.kevinschildhorn.fotopresenter.data.Path
import com.kevinschildhorn.fotopresenter.data.network.NetworkDirectoryDetails
import com.kevinschildhorn.fotopresenter.ui.UiState
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetAction
import com.kevinschildhorn.fotopresenter.ui.screens.common.ActionSheetContext
import com.kevinschildhorn.fotopresenter.ui.screens.common.ScreenState

/**
 * **DirectoryScreenUIState**
 *
 * The UiState of the Directory Screen, broken up into separate sub-states in order to help clarify
 * the complexity of the DirectoryScreen
 *
 *
 * **state** -                  The UiState, is present in every screens UiState
 *
 * **directoryGridUIState** -   The state of the main directory view, contains the list of folders,
 *                              images and the current path (Comes From Directory Navigator).
 *
 * **overlayUiState** -         The state of any views overlaying the directory screen. Can be for
 *                              actions or image preview (Comes From Image Preview Navigator).
 **/
data class DirectoryScreenUIState(
    override val state: UiState = UiState.IDLE,
    val searchText: String = "",
    var directoryGridUIState: DirectoryGridUIState =
        DirectoryGridUIState(
            Path.EMPTY,
            emptyList(),
            mutableListOf(),
        ),
    val overlayUiState: DirectoryOverlayUiState = DirectoryOverlayUiState.None,
    val slideshowDetails: ImageSlideshowDetails? = null,
) : ScreenState {
    fun getImageIndexFromId(id: Long?): Int = directoryGridUIState.imageStates.indexOfFirst { it.id == id }

    val currentPathList: List<Path>
        get() = directoryGridUIState.currentPath.pathList

    override fun toString(): String =
        """
        state:$state
        directoryGridUIState: $directoryGridUIState
        overlayUiState: $overlayUiState
        slideshowDetails: $slideshowDetails
        """.trimIndent()
}

/**
 * **DirectoryGridUIState**
 *
 * The UiState of Directory Grid, containing contents of the current directory
 *
 * **currentPath** -    The current path in the FTP Server that is displayed
 * **folderStates** -   The states for each folder displayed on screen
 * **imageStates** -    The states for each image displayed on screen
 **/
data class DirectoryGridUIState(
    val currentPath: Path,
    val folderStates: List<DirectoryGridCellUIState.Folder>,
    val imageStates: List<DirectoryGridCellUIState.Image>,
) {
    val allStates: List<DirectoryGridCellUIState>
        get() = folderStates + imageStates

    override fun toString(): String =
        """
        Directory Grid State:
        currentPath: '$currentPath'
        Folders: ${folderStates.count()}
            ${folderStates.joinToString(", ") { it.toString() }}
        Images: ${imageStates.count()}
            ${imageStates.joinToString(", ") { it.toString() }}
        """
}

/**
 * **DirectoryGridCellUIState**
 *
 * The UiState of a Directory GridCell, containing contents of a directory
 *
 *
 * **name** -                   The name of the file/folder
 *
 * **id** -                     The id of the file/folder
 *
 * **actionSheetContexts** -    The possible actions that can be performed on this cell
 **/
sealed class DirectoryGridCellUIState(
    val name: String,
    val id: Long,
    val actionSheetContexts: List<ActionSheetContext>,
) {
    class Folder(name: String, id: Long) : DirectoryGridCellUIState(
        name,
        id,
        listOf(
            ActionSheetContext(ActionSheetAction.START_SLIDESHOW, 1),
            ActionSheetContext(ActionSheetAction.ADD_DYNAMIC_LOCATION, 2),
        ),
    )

    class Image(val directoryDetails: NetworkDirectoryDetails, name: String, id: Long) :
        DirectoryGridCellUIState(
            name,
            id,
            listOf(
                ActionSheetContext(ActionSheetAction.ADD_STATIC_LOCATION, 1),
                ActionSheetContext(ActionSheetAction.SET_METADATA, 2),
            ),
        )

    override fun toString(): String = "(I:$name:$id)"
}

/**
 * **DirectoryOverlayUiState**
 *
 * The UiState of Overlays visible on the Directory Screen
 **/
sealed class DirectoryOverlayUiState {
    /**
     * **ImagePreview**
     *
     * The UiState of the Image Preview overlay
     *
     * **imageDirectory** - The image directory to display during the preview
     **/
    data class ImagePreview(val imageDirectory: NetworkDirectoryDetails) : DirectoryOverlayUiState()

    /**
     * **Actions**
     *
     * The UiState of the Action Sheet overlay, has sub-classes for each action. Should be self
     * explanatory
     **/
    sealed class Actions(
        val directoryUiState: DirectoryGridCellUIState,
        val directory: Directory,
    ) : DirectoryOverlayUiState() {
        class AddToPlaylist(
            directoryUiState: DirectoryGridCellUIState,
            directory: Directory,
        ) : Actions(directoryUiState, directory)

        class EditMetaData(
            val metadata: MetadataFileDetails?,
            directoryUiState: DirectoryGridCellUIState,
            directory: Directory,
        ) : Actions(directoryUiState, directory)

        class Sheet(
            directoryUiState: DirectoryGridCellUIState,
            directory: Directory,
        ) : Actions(directoryUiState, directory)
    }

    data object Sort : DirectoryOverlayUiState()

    data object LogoutConfirmation : DirectoryOverlayUiState()

    data object None : DirectoryOverlayUiState()

    @Suppress("UNCHECKED_CAST")
    fun <T : DirectoryOverlayUiState> castTo(): T? = this as? T
}
