package com.bloxes.itemdb.model.file

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateFileRequest (
        @field:NotBlank
        val file_name: String,
        @field:NotNull
        @field:Min(value=0)
        val file_total_size: Number
)