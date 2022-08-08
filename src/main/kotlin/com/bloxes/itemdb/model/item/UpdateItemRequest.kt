package com.bloxes.itemdb.model.item

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdateItemRequest (
        @field:NotBlank
        val item_name: String,
)