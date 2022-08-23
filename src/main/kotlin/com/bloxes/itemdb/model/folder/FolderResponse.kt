package com.bloxes.itemdb.model.folder

import com.bloxes.itemdb.model.file.NestedItem

data class FolderResponse(
        val id: String,
        val folder_name: String,
        val nested_folders: List<NestedFolder>,
        val items: List<NestedItem>,
        val created_at: String,
        val updated_at: String
)
