package com.bloxes.itemdb.mockedClass

import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.entity.File
import com.bloxes.itemdb.helper.RepositoryHelper
import com.bloxes.itemdb.model.folder.NestedFolder
import com.bloxes.itemdb.model.file.NestedItem

class RepositoryHelperMocked: RepositoryHelper {

    val nestedFolders = listOf(NestedFolder(id = "1", folder_name = "folder1"), NestedFolder(id = "2", folder_name = "folder2"))
    val items = listOf(NestedItem(id = "1", item_name = "item1"), NestedItem(id = "2", item_name = "item2"))

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
            items = items,
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