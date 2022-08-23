package com.bloxes.itemdb.controller

import com.bloxes.itemdb.model.WebResponse
import com.bloxes.itemdb.model.file.CreateFileRequest
import com.bloxes.itemdb.model.file.FileResponse
import com.bloxes.itemdb.model.file.UpdateFileRequest
import com.bloxes.itemdb.service.FileService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping(value = ["/api/v1/item"])
class ItemController(private val fileService: FileService) {

    @PostMapping(
            value = [""],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createItem(@RequestBody body: CreateFileRequest): WebResponse<FileResponse> {
        val itemResponse = fileService.createItem(body)
        return WebResponse(
                code = 200,
                status = "OK",
                data = itemResponse
        )
    }

    @GetMapping(
            value = [""],
            produces = ["application/json"]
    )
    fun getItem(@RequestParam("fileId") id: String): WebResponse<FileResponse> {
        val itemResponse = fileService.getItem(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = itemResponse
        )
    }

    @PutMapping(
            value = [""],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun updateItem(@RequestParam("fileId") id: String, @RequestBody updateFileRequest: UpdateFileRequest): WebResponse<FileResponse> {
        val itemResponse = fileService.updateItem(id, updateFileRequest)
        return WebResponse(
                code = 200,
                status = "OK",
                data = itemResponse
        )
    }

    @DeleteMapping(
            value = [""]
    )
    fun deleteItem(@RequestParam("fileId") id: String): WebResponse<String> {
        fileService.deleteItem(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = "file with id: $id is deleted"
        )

    }
}