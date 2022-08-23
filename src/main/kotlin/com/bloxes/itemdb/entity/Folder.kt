package com.bloxes.itemdb.entity

import com.bloxes.itemdb.model.folder.NestedFolder
import com.bloxes.itemdb.model.file.NestedFile
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "folder")
data class Folder (
    val id: String,
    var folder_name: String,
    var nested_folders: List<NestedFolder>,
    var files: List<NestedFile>,
//        var sharedUser: List<String>,
    val created_at: String,
    var updated_at: String
)