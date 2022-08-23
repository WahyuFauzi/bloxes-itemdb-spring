package com.bloxes.itemdb.helper

import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.entity.File

interface RepositoryHelper {
    fun findItemByIdOrThrowNotFound(id: String): File

    fun findFolderByIdOrThrowNotFound(id: String): Folder

    fun setListFolders(undiscovered: MutableList<String>, discovered: MutableList<String>)
}