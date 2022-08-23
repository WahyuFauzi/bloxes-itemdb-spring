package com.bloxes.itemdb.service

import com.bloxes.itemdb.model.file.CreateFileRequest
import com.bloxes.itemdb.model.file.FileResponse
import com.bloxes.itemdb.model.file.UpdateFileRequest

interface FileService {
    fun createFile(createFileRequest: CreateFileRequest): FileResponse

    fun getFile(id: String): FileResponse

    fun updateFile(id: String, updateFileRequest: UpdateFileRequest): FileResponse

    fun deleteFile(id: String)
}