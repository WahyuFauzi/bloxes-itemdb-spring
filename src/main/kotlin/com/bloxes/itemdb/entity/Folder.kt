package com.bloxes.itemdb.entity

import com.bloxes.itemdb.model.folder.NestedFolder
import com.bloxes.itemdb.model.item.NestedItem
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document(collection = "folder")
data class Folder (
        val id: String,
        var folder_name: String,
        var nested_folders: List<NestedFolder>,
        var items: List<NestedItem>,
        val created_at: Date,
        var updated_at: Date
)