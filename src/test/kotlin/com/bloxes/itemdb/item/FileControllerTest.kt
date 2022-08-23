package com.bloxes.itemdb.item

import com.bloxes.itemdb.controller.FileController
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

    private val fileController: FileController = FileController(fileService)

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
        data = "file with id: $id is deleted"
    )

    @Test
    fun initTest() {
        assertInstanceOf(FileController::class.java, fileController)
    }

    @Test
    fun createFile() {
        `when`(fileService.createFile(dummyCreateFileRequest)).thenReturn(dummyFileResponse)
        val webResponse: WebResponse<FileResponse> = fileController.createFile(dummyCreateFileRequest)
        verify(fileService).createFile(dummyCreateFileRequest)
        assertEquals(webResponse, dummyWebResponse)
    }

    @Test
    fun getFile() {
        `when`(fileService.getFile(id)).thenReturn(dummyFileResponse)
        val webResponse: WebResponse<FileResponse> = fileController.getFile(id)
        verify(fileService).getFile(id)
        assertEquals(webResponse, dummyWebResponse)
    }

    @Test
    fun updateFile() {
        `when`(fileService.updateFile(id, dummyUpdateFileRequest)).thenReturn(dummyFileResponse)
        val webResponse: WebResponse<FileResponse> = fileController.updateFile(id, dummyUpdateFileRequest)
        verify(fileService).updateFile(id, dummyUpdateFileRequest)
        assertEquals(webResponse, dummyWebResponse)
    }

    @Test
    fun deleteFile() {
        val webResponse: WebResponse<String> = fileController.deleteFile(id)
        verify(fileService).deleteFile(id)
        assertEquals(webResponse, dummyWebResponseString)
    }
}