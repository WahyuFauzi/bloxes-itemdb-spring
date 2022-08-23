package com.bloxes.itemdb.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "item")
data class File (
        val id: String,
        var file_name: String,
        var file_total_size: Number,
        val created_at: String,
        var updated_at: String
        )