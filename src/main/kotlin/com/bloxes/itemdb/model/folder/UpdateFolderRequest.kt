package com.bloxes.itemdb.model.folder

import com.bloxes.itemdb.model.item.NestedItem
import javax.validation.constraints.NotBlank

data class UpdateFolderRequest(
        @field:NotBlank
        val folder_name: String,
        val nested_folders: List<NestedFolder>,
        val items: List<NestedItem>,
)
