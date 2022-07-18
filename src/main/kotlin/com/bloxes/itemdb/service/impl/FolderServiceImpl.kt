package com.bloxes.itemdb.service.impl

import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.helper.Helper
import com.bloxes.itemdb.model.folder.CreateFolderRequest
import com.bloxes.itemdb.model.folder.FolderResponse
import com.bloxes.itemdb.model.folder.UpdateFolderRequest
import com.bloxes.itemdb.repository.FolderRepository
import com.bloxes.itemdb.service.FolderService
import com.bloxes.itemdb.validator.ValidatorUtil
import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class FolderServiceImpl(private val folderRepository: FolderRepository, val validator: ValidatorUtil, val helper: Helper): FolderService {
    override fun createFolder(createFolderRequest: CreateFolderRequest): FolderResponse {
        validator.validate(createFolderRequest)

        val folder = Folder(
            id = UUID.randomUUID().toString(),
            folder_name = createFolderRequest.folder_name,
            nested_folders = listOf(),
            items = listOf(),
            created_at = Date(),
            updated_at = Date()
        )

        runBlocking {
            launch { folderRepository.insert(folder) }
        }

        return helper.folderToFolderResponse(folder)
    }

    override fun getFolder(id: String): FolderResponse {
        val folder =  helper.folderOrNull(folderRepository.findById(id).get())
        return helper.folderToFolderResponse(folder)
    }

    override fun getListNestedFolders(undiscovered: MutableList<String>, discovered: MutableList<String>) {
        // if undiscovered is not empty, then run this code asynchronously, if its empty then break the loop
        if(undiscovered.size > 0){
            runBlocking {
                undiscovered.map {
                    async {
                        discovered.add(it) // add the undiscovered to discovered
                        addIdToUndiscovered(undiscovered, it) // add the nested folders id to undiscovered
                        undiscovered.remove(it)// delete the already discovered id from undiscovered
                    }
                }.awaitAll()

                getListNestedFolders(undiscovered, discovered)
            }
        }
    }

    override fun updateFolder(id: String, updateFolderRequest: UpdateFolderRequest): FolderResponse {
        validator.validate(updateFolderRequest)

        val folder = helper.folderOrNull(folderRepository.findById(id).get())
        folder.folder_name = updateFolderRequest.folder_name
        folder.nested_folders = updateFolderRequest.nested_folders
        folder.items = updateFolderRequest.items
        folder.updated_at = Date()
        runBlocking {
            launch {
                folderRepository.save(folder)
            }
        }
        return helper.folderToFolderResponse(folder)
    }

    override fun deleteFolder(id: String) {
        runBlocking {
            launch {
                folderRepository.deleteById(id)
            }
        }
    }

    // should I modularize this method?
    suspend fun addIdToUndiscovered(undiscovered: MutableList<String>, id: String) {
        val folder =  folderRepository.findById(id).get()
        folder.nested_folders.map {
            undiscovered.add(it.id)
        }
    }
}