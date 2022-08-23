package com.bloxes.itemdb.item

import com.bloxes.itemdb.controller.ItemController
import com.bloxes.itemdb.model.WebResponse
import com.bloxes.itemdb.model.file.CreateFileRequest
import com.bloxes.itemdb.model.file.FileResponse
import com.bloxes.itemdb.model.file.UpdateFileRequest
import com.bloxes.itemdb.service.FileService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class FileControllerTest {

    @Mock
    val fileService: FileService = mock(FileService::class.java)

    private val itemController: ItemController = ItemController(fileService)

    private val id = "id"

    private val dummyCreateFileRequest: CreateFileRequest = CreateFileRequest(
        file_name = "joseph joestar",
        file_total_size = 69420
    )

    private val dummyUpdateFileRequest: UpdateFileRequest = UpdateFileRequest(
        file_name = "joseph joestar"
    )

    private val dummyFileResponse: FileResponse = FileResponse(
        id = "id",
        file_name = "joseph joestar",
        file_total_size = 69420,
        created_at = "24/12/1999",
        updated_at = "24/12/1999"
    )

    private val dummyWebResponse: WebResponse<FileResponse> = WebResponse(
        code = 200,
        status = "OK",
        data = dummyFileResponse
    )

    private val dummyWebResponseString: WebResponse<String> = WebResponse(
        code = 200,
        status = "OK",
        data = "item with id: $id is deleted"
    )

    @Test
    fun initTest() {
        assertInstanceOf(ItemController::class.java, itemController)
    }

    @Test
    fun createItem() {
        `when`(fileService.createItem(dummyCreateFileRequest)).thenReturn(dummyFileResponse)
        val webResponse: WebResponse<FileResponse> = itemController.createItem(dummyCreateFileRequest)
        verify(fileService).createItem(dummyCreateFileRequest)
        assertEquals(webResponse, dummyWebResponse)
    }

    @Test
    fun getItem() {
        `when`(fileService.getItem(id)).thenReturn(dummyFileResponse)
        val webResponse: WebResponse<FileResponse> = itemController.getItem(id)
        verify(fileService).getItem(id)
        assertEquals(webResponse, dummyWebResponse)
    }

    @Test
    fun updateItem() {
        `when`(fileService.updateItem(id, dummyUpdateFileRequest)).thenReturn(dummyFileResponse)
        val webResponse: WebResponse<FileResponse> = itemController.updateItem(id, dummyUpdateFileRequest)
        verify(fileService).updateItem(id, dummyUpdateFileRequest)
        assertEquals(webResponse, dummyWebResponse)
    }

    @Test
    fun deleteItem() {
        val webResponse: WebResponse<String> = itemController.deleteItem(id)
        verify(fileService).deleteItem(id)
        assertEquals(webResponse, dummyWebResponseString)
    }
}