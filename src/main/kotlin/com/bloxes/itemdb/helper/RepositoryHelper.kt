package com.bloxes.itemdb.helper

import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.entity.Item
import com.bloxes.itemdb.error.NotFoundException
import org.springframework.data.repository.findByIdOrNull

interface RepositoryHelper {
    fun findItemByIdOrThrowNotFound(id: String): Item

    fun findFolderByIdOrThrowNotFound(id: String): Folder

    fun setListFolders(undiscovered: MutableList<String>, discovered: MutableList<String>)
}