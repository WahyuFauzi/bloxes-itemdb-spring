package com.bloxes.itemdb.service

import com.bloxes.itemdb.model.folder.CreateFolderRequest
import com.bloxes.itemdb.model.folder.FolderResponse
import com.bloxes.itemdb.model.folder.UpdateFolderRequest

interface FolderService {
    fun createFolder(createFolderRequest: CreateFolderRequest): FolderResponse

    fun getFolder(id: String): FolderResponse

    fun getListNestedFolders(undiscovered: MutableList<String>, discovered: MutableList<String>)

    fun updateFolder(id: String, updateFolderRequest: UpdateFolderRequest): FolderResponse

    fun deleteFolder(id: String)
}