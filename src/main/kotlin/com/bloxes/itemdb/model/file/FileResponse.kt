package com.bloxes.itemdb.model.file

data class FileResponse (
        val id: String,
        val file_name: String,
        val file_total_size: Number,
        val created_at: String,
        val updated_at: String
)