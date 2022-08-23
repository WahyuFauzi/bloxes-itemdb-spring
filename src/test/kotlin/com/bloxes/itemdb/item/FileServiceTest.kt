package com.bloxes.itemdb.item

import com.bloxes.itemdb.entity.File
import com.bloxes.itemdb.helper.Helper
import com.bloxes.itemdb.model.file.CreateFileRequest
import com.bloxes.itemdb.model.file.FileResponse
import com.bloxes.itemdb.model.file.UpdateFileRequest
import com.bloxes.itemdb.repository.FileRepository
import com.bloxes.itemdb.service.impl.FileServiceImpl
import com.bloxes.itemdb.helper.DateHelper
import com.bloxes.itemdb.helper.RepositoryHelperImpl
import com.bloxes.itemdb.helper.UUIDHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class FileServiceTest {

    @Mock
    private val fileRepository = mock(FileRepository::class.java)

    @Mock
    val dateHelper: DateHelper = mock(DateHelper::class.java)

    @Mock
    val repoHelper: RepositoryHelperImpl = mock(RepositoryHelperImpl::class.java)

    @Mock
    val uuidHelper: UUIDHelper = mock(UUIDHelper::class.java)

    private val helper: Helper = Helper()

    private val itemService: FileServiceImpl = FileServiceImpl(fileRepository, dateHelper, repoHelper, uuidHelper, helper)

    private val id = "id"

    private val file: File = File(
        id = "id",
        file_name = "joseph joestar",
        file_total_size = 69420,
        created_at = "24/12/1999",
        updated_at = "24/12/1999"
    )

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

    @Test
    fun initTest() {
        assertInstanceOf(FileServiceImpl::class.java, itemService)
    }

    @Test
    fun createItem() {
        `when`(uuidHelper.getRandomUUID()).thenReturn(id)
        `when`(dateHelper.getCurrentDateInString()).thenReturn("24/12/1999")
        val fileResponse: FileResponse = itemService.createItem(dummyCreateFileRequest)
        assertEquals(fileResponse, dummyFileResponse)
        verify(uuidHelper).getRandomUUID()
        verify(dateHelper, times(2)).getCurrentDateInString()
        verify(fileRepository).save(file)
    }

    @Test
    fun getItem() {
        `when`(repoHelper.findItemByIdOrThrowNotFound(id)).thenReturn(file)
        val fileResponse: FileResponse = itemService.getItem(id)
        assertEquals(fileResponse, dummyFileResponse)
        verify(repoHelper).findItemByIdOrThrowNotFound(id)
    }

    @Test
    fun updateItem() {
        `when`(repoHelper.findItemByIdOrThrowNotFound(id)).thenReturn(file)
        `when`(dateHelper.getCurrentDateInString()).thenReturn("24/12/1999")
        val fileResponse: FileResponse = itemService.updateItem(id, dummyUpdateFileRequest)
        assertEquals(fileResponse, dummyFileResponse)
        verify(repoHelper).findItemByIdOrThrowNotFound(id)
        verify(dateHelper).getCurrentDateInString()
    }
}