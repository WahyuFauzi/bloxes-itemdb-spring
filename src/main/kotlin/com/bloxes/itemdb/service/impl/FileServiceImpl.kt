package com.bloxes.itemdb.service.impl

import com.bloxes.itemdb.entity.File
import com.bloxes.itemdb.helper.Helper
import com.bloxes.itemdb.model.file.CreateFileRequest
import com.bloxes.itemdb.model.file.FileResponse
import com.bloxes.itemdb.model.file.UpdateFileRequest
import com.bloxes.itemdb.repository.FileRepository
import com.bloxes.itemdb.service.FileService
import com.bloxes.itemdb.helper.DateHelper
import com.bloxes.itemdb.helper.RepositoryHelperImpl
import com.bloxes.itemdb.helper.UUIDHelper
import kotlinx.coroutines.*
import org.springframework.stereotype.Service

@Service
class FileServiceImpl(
    private val fileRepository: FileRepository,
    private val dateHelper: DateHelper,
    private val repoHelper: RepositoryHelperImpl,
    private val uuidHelper: UUIDHelper,
    private val helper: Helper
) : FileService {
    override fun createItem(createFileRequest: CreateFileRequest): FileResponse {
        val file = File(
            id = uuidHelper.getRandomUUID(),
            file_name = createFileRequest.file_name,
            file_total_size = createFileRequest.file_total_size,
            created_at = dateHelper.getCurrentDateInString(),
            updated_at = dateHelper.getCurrentDateInString()
        )
        fileRepository.save(file)
        return helper.itemToFileResponse(file)
    }

    override fun getItem(id: String): FileResponse {
        val item = repoHelper.findItemByIdOrThrowNotFound(id)
        return helper.itemToFileResponse(item)
    }

    override fun updateItem(id: String, updateFileRequest: UpdateFileRequest): FileResponse {
        val item = repoHelper.findItemByIdOrThrowNotFound(id)
        item.apply {
            file_name = updateFileRequest.file_name
            updated_at = dateHelper.getCurrentDateInString()
        }
        fileRepository.save(item)
        return helper.itemToFileResponse(item)
    }

    override fun deleteItem(id: String) {
        runBlocking {
            launch {
                fileRepository.deleteById(id)
            }
        }
    }
}