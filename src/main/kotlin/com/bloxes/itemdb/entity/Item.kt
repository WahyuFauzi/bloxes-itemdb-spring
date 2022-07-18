package com.bloxes.itemdb.entity

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "item")
data class Item (
        val id: String,
        var item_name: String,
        var item_total_size: Number,
        val created_at: Date,
        var updated_at: Date
        )