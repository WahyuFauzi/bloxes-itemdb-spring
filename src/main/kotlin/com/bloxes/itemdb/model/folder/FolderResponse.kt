package com.bloxes.itemdb.model.folder

import com.bloxes.itemdb.model.item.NestedItem
import java.util.*

data class FolderResponse(
        val id: String,
        val folder_name: String,
        val nested_folders: List<NestedFolder>,
        val items: List<NestedItem>,
        val created_at: Date,
        val updated_at: Date
)
