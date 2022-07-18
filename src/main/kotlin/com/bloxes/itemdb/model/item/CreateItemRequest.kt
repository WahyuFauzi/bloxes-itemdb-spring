package com.bloxes.itemdb.model.item

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateItemRequest (
        @field:NotBlank
        val item_name: String,
        @field:NotNull
        @field:Min(value=0)
        val item_total_size: Number
)