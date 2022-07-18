package com.bloxes.itemdb.model.item

import java.util.*

data class ItemResponse (
        val id: String,
        val item_name: String,
        val item_total_size: Number,
        val created_at: Date,
        val updated_at: Date
)