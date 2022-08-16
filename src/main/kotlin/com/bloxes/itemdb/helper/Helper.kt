package com.bloxes.itemdb.helper

import com.bloxes.itemdb.error.NotFoundException
import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.entity.Item
import com.bloxes.itemdb.model.folder.FolderResponse
import com.bloxes.itemdb.model.item.ItemResponse
import org.springframework.stereotype.Component

@Component
class Helper {
    fun itemToItemResponse(item: Item): ItemResponse {
        return ItemResponse(
                id = item.id,
                item_name = item.item_name,
                item_total_size = item.item_total_size,
                created_at = item.created_at,
                updated_at = item.updated_at
        )
    }

    fun folderToFolderResponse(folder: Folder): FolderResponse {
        return FolderResponse(
                id = folder.id,
                folder_name = folder.folder_name,
                nested_folders = folder.nested_folders,
                items = folder.items,
                created_at = folder.created_at,
                updated_at = folder.updated_at
        )
    }

    fun folderOrNull(folder: Folder): Folder {
        if(folder === null) {
            throw NotFoundException()
        } else {
            return folder
        }
    }
}