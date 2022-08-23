package com.bloxes.itemdb.service

import com.bloxes.itemdb.model.file.CreateFileRequest
import com.bloxes.itemdb.model.file.FileResponse
import com.bloxes.itemdb.model.file.UpdateFileRequest

interface FileService {
    fun createItem(createFileRequest: CreateFileRequest): FileResponse

    fun getItem(id: String): FileResponse

    fun updateItem(id: String, updateFileRequest: UpdateFileRequest): FileResponse

    fun deleteItem(id: String)
}