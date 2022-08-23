package com.bloxes.itemdb.helper

import com.bloxes.itemdb.entity.Folder
import com.bloxes.itemdb.entity.File
import com.bloxes.itemdb.model.folder.FolderResponse
import com.bloxes.itemdb.model.file.FileResponse
import org.springframework.stereotype.Component

@Component
class Helper {
    fun itemToFileResponse(file: File): FileResponse {
        return FileResponse(
                id = file.id,
                file_name = file.file_name,
                file_total_size = file.file_total_size,
                created_at = file.created_at,
                updated_at = file.updated_at
        )
    }

    fun folderToFolderResponse(folder: Folder): FolderResponse {
        return FolderResponse(
                id = folder.id,
                folder_name = folder.folder_name,
                nested_folders = folder.nested_folders,
                files = folder.files,
                created_at = folder.created_at,
                updated_at = folder.updated_at
        )
    }
}