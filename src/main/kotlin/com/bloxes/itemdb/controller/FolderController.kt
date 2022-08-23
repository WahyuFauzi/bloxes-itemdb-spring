package com.bloxes.itemdb.controller

import com.bloxes.itemdb.model.WebResponse
import com.bloxes.itemdb.model.folder.CreateFolderRequest
import com.bloxes.itemdb.model.folder.FolderResponse
import com.bloxes.itemdb.model.folder.UpdateFolderRequest
import com.bloxes.itemdb.service.FolderService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping(value = ["/api/v1/folder"])
class FolderController(private val folderService: FolderService) {
        @PostMapping(
        value = [""],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createFolder(@RequestBody body: CreateFolderRequest): WebResponse<FolderResponse> {
            println(body)
        val folderResponse = folderService.createFolder(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = folderResponse
        )
    }
    @GetMapping(
        value = [""],
        produces = ["application/json"]
    )
    fun getFolder(@RequestParam("folderId") id: String): WebResponse<FolderResponse>{
        val folderResponse = folderService.getFolder(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = folderResponse
        )
    }

    @GetMapping(
        value = ["/list-nested"],
        produces = ["application/json"]
    )
    fun getListNestedFolders(@PathVariable("folderId") id: String): WebResponse<List<String>> {
        val discoveredList = folderService.getListNestedFolders(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = discoveredList
        )
    }

    @PutMapping(
        value = [""],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateFolder(@RequestParam("folderId") id: String, @RequestBody updateFolderRequest: UpdateFolderRequest): WebResponse<FolderResponse> {
        val folderResponse = folderService.updateFolder(id, updateFolderRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = folderResponse
        )
    }

    @DeleteMapping(
        value = [""]
    )
    fun deleteFolder(@RequestParam("folderId") id: String): WebResponse<String> {
        folderService.deleteFolder(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "folder with id: $id is deleted"
        )
    }

    @DeleteMapping(
        value = ["/delete-with-nested"]
    )
    fun deleteFolderWithNestedFolder(@PathVariable("folderId") id: String): WebResponse<String> {
        val listNestedFolders = folderService.getListNestedFolders(id)
        listNestedFolders.map {
            folderService.deleteFolder(it)
        }
        return WebResponse(
            code = 200,
            status = "OK",
            data = "folder with id: $id is deleted with it nested folders"
        )
    }
}