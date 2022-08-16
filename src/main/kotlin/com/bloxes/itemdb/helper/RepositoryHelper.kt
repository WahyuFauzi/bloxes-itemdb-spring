package com.bloxes.userdb.helper

import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.entity.Item
import com.bloxes.itemdb.error.NotFoundException
import com.bloxes.itemdb.repository.FolderRepository
import com.bloxes.itemdb.repository.ItemRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class RepositoryHelper(private val itemRepository: ItemRepository, private val folderRepository: FolderRepository) {
    fun findItemByIdOrThrowNotFound(id: String): Item {
        val item = itemRepository.findByIdOrNull(id)
        if (item == null) {
            throw NotFoundException()
        } else {
            return item;
        }
    }

    fun findFolderByIdOrThrowNotFound(id: String): Folder {
        val folder = folderRepository.findByIdOrNull(id)
        if(folder == null) {
            throw NotFoundException()
        } else {
            return folder
        }
    }

    suspend fun addIdToUndiscovered(undiscovered: MutableList<String>, id: String): MutableList<String> {
        val folder = folderRepository.findByIdOrNull(id)
        if(folder == null) {
            throw NotFoundException()
        } else {
            folder.nested_folders.map {
                undiscovered.add(it.id)
            }
            return undiscovered
        }
    }
}