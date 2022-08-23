package com.bloxes.itemdb.model.folder

import com.bloxes.itemdb.model.file.NestedFile
import javax.validation.constraints.NotBlank

data class UpdateFolderRequest(
    @field:NotBlank
        val folder_name: String,
    val nested_folders: List<NestedFolder>,
    val files: List<NestedFile>,
)
