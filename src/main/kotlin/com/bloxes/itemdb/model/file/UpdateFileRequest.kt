package com.bloxes.itemdb.model.file

import javax.validation.constraints.NotBlank

data class UpdateFileRequest (
        @field:NotBlank
        val file_name: String,
)