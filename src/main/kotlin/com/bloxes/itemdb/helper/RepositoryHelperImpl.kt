package com.bloxes.itemdb.helper

import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.entity.File
import com.bloxes.itemdb.error.NotFoundException
import com.bloxes.itemdb.repository.FolderRepository
import com.bloxes.itemdb.repository.FileRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class RepositoryHelperImpl(private val fileRepository: FileRepository, private val folderRepository: FolderRepository): RepositoryHelper {
    override fun findItemByIdOrThrowNotFound(id: String): File {
        val item = fileRepository.findByIdOrNull(id)
        if (item == null) {
            throw NotFoundException()
        } else {
            return item;
        }
    }

    override fun findFolderByIdOrThrowNotFound(id: String): Folder {
        val folder = folderRepository.findByIdOrNull(id)
        if(folder == null) {
            throw NotFoundException()
        } else {
            return folder
        }
    }

    override fun setListFolders(undiscovered: MutableList<String>, discovered: MutableList<String>) {
        if(undiscovered.size > 0) {
            undiscovered.map{ it ->
                val folder = findFolderByIdOrThrowNotFound(it)
                folder.nested_folders.map {
                    undiscovered.add(it.id)
                }
                discovered.add(it)
                undiscovered.remove(it)
                setListFolders(undiscovered, discovered)
            }
        }
    }
}