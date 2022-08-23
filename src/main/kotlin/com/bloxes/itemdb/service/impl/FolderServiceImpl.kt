package com.bloxes.itemdb.service.impl

import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.helper.*
import com.bloxes.itemdb.model.folder.CreateFolderRequest
import com.bloxes.itemdb.model.folder.FolderResponse
import com.bloxes.itemdb.model.folder.UpdateFolderRequest
import com.bloxes.itemdb.repository.FolderRepository
import com.bloxes.itemdb.service.FolderService
import com.bloxes.itemdb.repository.FileRepository
import org.springframework.stereotype.Service

@Service
class FolderServiceImpl(
    private val folderRepository: FolderRepository,
    private val fileRepository: FileRepository,
    val dateHelper: DateHelper,
    val repoHelper: RepositoryHelper,
    val uuidHelper: UUIDHelper,
    val helper: Helper
    ) : FolderService {
    override fun createFolder(createFolderRequest: CreateFolderRequest): FolderResponse {
        val folder = Folder(
            id = uuidHelper.getRandomUUID(),
            folder_name = createFolderRequest.folder_name,
            nested_folders = listOf(),
            files = listOf(),
            created_at = dateHelper.getCurrentDateInString(),
            updated_at = dateHelper.getCurrentDateInString()
        )
        folderRepository.save(folder)
        return helper.folderToFolderResponse(folder)
    }

    override fun getFolder(id: String): FolderResponse {
        val folder = repoHelper.findFolderByIdOrThrowNotFound(id)
        return helper.folderToFolderResponse(folder)
    }

    override fun getListNestedFolders(id: String): MutableList<String> {
        val undiscovered = mutableListOf(id)
        val discovered = mutableListOf<String>()
        repoHelper.setListFolders(undiscovered, discovered)
        return discovered
    }

    override fun updateFolder(id: String, updateFolderRequest: UpdateFolderRequest): FolderResponse {
        val folder = repoHelper.findFolderByIdOrThrowNotFound(id)
        folder.folder_name = updateFolderRequest.folder_name
        folder.nested_folders = updateFolderRequest.nested_folders
        folder.files = updateFolderRequest.files
        folder.updated_at = dateHelper.getCurrentDateInString()
        folderRepository.save(folder)
        return helper.folderToFolderResponse(folder)
    }

    override fun deleteFolder(id: String) {
        val nestedItems = repoHelper.findFolderByIdOrThrowNotFound(id).files
        nestedItems.map {
            fileRepository.deleteById(it.id)
        }
        folderRepository.deleteById(id)
    }
}