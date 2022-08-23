package com.bloxes.itemdb.folder

import com.bloxes.itemdb.controller.FolderController
import com.bloxes.itemdb.model.WebResponse
import com.bloxes.itemdb.model.folder.CreateFolderRequest
import com.bloxes.itemdb.model.folder.FolderResponse
import com.bloxes.itemdb.model.folder.NestedFolder
import com.bloxes.itemdb.model.folder.UpdateFolderRequest
import com.bloxes.itemdb.model.file.NestedItem
import com.bloxes.itemdb.service.FolderService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class FolderControllerTest {

    @Mock
    val folderService: FolderService = mock(FolderService::class.java)

    private val id: String = "id"

    private val folderController: FolderController = FolderController(folderService)

    private val dummyList = mutableListOf("id1", "id2", "id3")

    private val dummyListFolder = mutableListOf<NestedFolder>()

    private val dummyListItem = mutableListOf<NestedItem>()

    private val dummyFolderResponse: FolderResponse = FolderResponse(
        id = id,
        folder_name = "dummy folder",
        nested_folders = listOf(),
        items = listOf(),
        created_at = "24/12/1999",
        updated_at = "24/12/1999"
    )

    private val dummyWebResponse: WebResponse<FolderResponse> = WebResponse(
        code = 200,
        status = "OK",
        data = dummyFolderResponse
    )

    private val dummyWebResponseListFolder = WebResponse(
        code = 200,
        status = "OK",
        data = dummyList
    )

    private val dummyCreateFolderRequest: CreateFolderRequest = CreateFolderRequest(folder_name = "dummy folder")

    private val dummyUpdateFolderRequest: UpdateFolderRequest = UpdateFolderRequest(
        folder_name = "dummy folder",
        nested_folders = dummyListFolder,
        items = dummyListItem
    )

    @Test
    fun initTest() {
        assertInstanceOf(FolderController::class.java, folderController)
    }

    @Test
    fun createFolder() {
        `when`(folderService.createFolder(dummyCreateFolderRequest)).thenReturn(dummyFolderResponse)
        val webResponse = folderController.createFolder(dummyCreateFolderRequest)
        assertEquals(webResponse, dummyWebResponse)
        verify(folderService).createFolder(dummyCreateFolderRequest)
    }

    @Test
    fun getFolder() {
        `when`(folderService.getFolder(id)).thenReturn(dummyFolderResponse)
        val webResponse = folderController.getFolder(id)
        assertEquals(webResponse, dummyWebResponse)
        verify(folderService).getFolder(id)
    }

    @Test
    fun getListNestedFolders() {
        `when`(folderService.getListNestedFolders(id)).thenReturn(dummyList)
        val webResponse = folderController.getListNestedFolders(id)
        assertEquals(webResponse, dummyWebResponseListFolder)
        verify(folderService).getListNestedFolders(id)
    }

    @Test
    fun updateFolder() {
        `when`(folderService.updateFolder(id, dummyUpdateFolderRequest)).thenReturn(dummyFolderResponse)
        val webResponse = folderController.updateFolder(id, dummyUpdateFolderRequest)
        assertEquals(webResponse, dummyWebResponse)
        verify(folderService).updateFolder(id, dummyUpdateFolderRequest)
    }

    @Test
    fun deleteFolder() {
        val dummyWebResponseString = WebResponse(
            code = 200,
            status = "OK",
            data = "folder with id: $id is deleted"
        )
        val webResponse = folderController.deleteFolder(id)
        assertEquals(webResponse, dummyWebResponseString)
        verify(folderService).deleteFolder(id)
    }

    @Test
    fun deleteWithNestedFolder() {
        val deleteWithNestedFolderWebResponse = WebResponse(
            code = 200,
            status = "OK",
            data = "folder with id: $id is deleted with it nested folders"
        )
        `when`(folderService.getListNestedFolders(id)).thenReturn(dummyList)
        val webResponse = folderController.deleteFolderWithNestedFolder(id)
        assertEquals(webResponse, deleteWithNestedFolderWebResponse)
        verify(folderService).getListNestedFolders(id)
        verify(folderService, atLeast(1)).deleteFolder("id1")

    }
}