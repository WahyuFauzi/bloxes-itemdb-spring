package com.bloxes.itemdb.model.folder

import javax.validation.constraints.NotBlank

data class CreateFolderRequest(
        @field:NotBlank
        val folder_name: String
)