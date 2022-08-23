package com.bloxes.itemdb.folder

import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.helper.DateHelper
import com.bloxes.itemdb.helper.Helper
import com.bloxes.itemdb.helper.UUIDHelper
import com.bloxes.itemdb.mockedClass.RepositoryHelperMocked
import com.bloxes.itemdb.model.folder.CreateFolderRequest
import com.bloxes.itemdb.model.folder.FolderResponse
import com.bloxes.itemdb.model.folder.NestedFolder
import com.bloxes.itemdb.model.folder.UpdateFolderRequest
import com.bloxes.itemdb.model.file.NestedFile
import com.bloxes.itemdb.repository.FolderRepository
import com.bloxes.itemdb.repository.FileRepository
import com.bloxes.itemdb.service.impl.FolderServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class FolderServiceTest {

    @Mock
    private val folderRepository = mock(FolderRepository::class.java)

    @Mock
    private val fileRepository = mock(FileRepository::class.java)

    @Mock
    val dateHelper: DateHelper = mock(DateHelper::class.java)

    private val repoHelper = RepositoryHelperMocked()

    @Mock
    val uuidHelper: UUIDHelper = mock(UUIDHelper::class.java)

    private val id = "id"

    private val date = "24/12/1999"

    private val helper: Helper = Helper()

    private val folderService = FolderServiceImpl(folderRepository, fileRepository, dateHelper, repoHelper, uuidHelper, helper)

    private val dummyFolder: Folder = Folder(
        id = id,
        folder_name = "dummy folder",
        nested_folders = listOf(),
        files = listOf(),
        created_at = "24/12/1999",
        updated_at = "24/12/1999"
    )

    private val dummyFolderResponseEmpty: FolderResponse = FolderResponse(
        id = id,
        folder_name = "dummy folder",
        nested_folders = listOf(),
        files = listOf(),
        created_at = "24/12/1999",
        updated_at = "24/12/1999"
    )

    private val dummyFolderResponse: FolderResponse = FolderResponse(
        id = id,
        folder_name = "dummy folder",
        nested_folders = repoHelper.nestedFolders,
        files = repoHelper.files,
        created_at = "24/12/1999",
        updated_at = "24/12/1999"
    )

    private val dummyListFolder = mutableListOf<NestedFolder>()

    private val dummyListItem = mutableListOf<NestedFile>()

    private val dummyCreateFolderRequest: CreateFolderRequest = CreateFolderRequest(folder_name = "dummy folder")

    private val dummyUpdateFolderRequest: UpdateFolderRequest = UpdateFolderRequest(
        folder_name = "dummy folder",
        nested_folders = dummyListFolder,
        files = dummyListItem
    )

    @Test
    fun createFolder() {
        `when`(uuidHelper.getRandomUUID()).thenReturn(id)
        `when`(dateHelper.getCurrentDateInString()).thenReturn(date)
        val folderResponse = folderService.createFolder(dummyCreateFolderRequest)
        assertEquals(folderResponse, dummyFolderResponseEmpty)
        verify(folderRepository).save(dummyFolder)
        verify(uuidHelper).getRandomUUID()
        verify(dateHelper, times(2)).getCurrentDateInString()
    }

    @Test
    fun getFolder() {
        val folderResponse = folderService.getFolder(id)
        assertEquals(folderResponse, dummyFolderResponse)
    }

    @Test
    fun getListNestedFolders() {
        val dummyList = mutableListOf("id1", "id2", "id3")
        val listFolder = folderService.getListNestedFolders(id)
        assertEquals(listFolder, dummyList)
    }

    @Test
    fun updateFolder() {
        `when`(dateHelper.getCurrentDateInString()).thenReturn(date)
        val folderResponse = folderService.updateFolder(id, dummyUpdateFolderRequest)
        assertEquals(folderResponse, dummyFolderResponseEmpty)
        verify(folderRepository).save(dummyFolder)
    }

    @Test
    fun deleteFolder() {
        folderService.deleteFolder(id)
        verify(folderRepository).deleteById(id)
        verify(fileRepository, atLeast(1)).deleteById("1")
    }

}