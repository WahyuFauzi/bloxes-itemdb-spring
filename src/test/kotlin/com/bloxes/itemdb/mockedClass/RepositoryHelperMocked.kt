package com.bloxes.itemdb.mockedClass

import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.entity.File
import com.bloxes.itemdb.helper.RepositoryHelper
import com.bloxes.itemdb.model.folder.NestedFolder
import com.bloxes.itemdb.model.file.NestedFile

class RepositoryHelperMocked: RepositoryHelper {

    val nestedFolders = listOf(NestedFolder(id = "1", folder_name = "folder1"), NestedFolder(id = "2", folder_name = "folder2"))
    val files = listOf(NestedFile(id = "1", file_name = "item1"), NestedFile(id = "2", file_name = "item2"))

    override fun findItemByIdOrThrowNotFound(id: String): File {
         return File(
            id = "id",
            file_name = "joseph joestar",
            file_total_size = 69420,
            created_at = "24/12/1999",
            updated_at = "24/12/1999"
        )
    }

    override fun findFolderByIdOrThrowNotFound(id: String): Folder {
        return Folder(
            id = "id",
            folder_name = "dummy folder",
            nested_folders = nestedFolders,
            files = files,
            created_at = "24/12/1999",
            updated_at = "24/12/1999"
        )
    }

    override fun setListFolders(undiscovered: MutableList<String>, discovered: MutableList<String>) {
        val dummyList = mutableListOf("id1", "id2", "id3")
        dummyList.map {
            discovered.add(it)
        }
    }
}