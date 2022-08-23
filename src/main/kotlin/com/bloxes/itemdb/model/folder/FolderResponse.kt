package com.bloxes.itemdb.model.folder

import com.bloxes.itemdb.model.file.NestedFile

data class FolderResponse(
        val id: String,
        val folder_name: String,
        val nested_folders: List<NestedFolder>,
        val files: List<NestedFile>,
        val created_at: String,
        val updated_at: String
)
