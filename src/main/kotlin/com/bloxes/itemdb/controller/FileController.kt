package com.bloxes.itemdb.controller

import com.bloxes.itemdb.model.WebResponse
import com.bloxes.itemdb.model.file.CreateFileRequest
import com.bloxes.itemdb.model.file.FileResponse
import com.bloxes.itemdb.model.file.UpdateFileRequest
import com.bloxes.itemdb.service.FileService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = arrayOf("*"))
@RestController
@RequestMapping(value = ["/api/v1/file"])
class FileController(private val fileService: FileService) {

    @PostMapping(
            value = [""],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createFile(@RequestBody body: CreateFileRequest): WebResponse<FileResponse> {
        val itemResponse = fileService.createFile(body)
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
    fun getFile(@RequestParam("fileId") id: String): WebResponse<FileResponse> {
        val itemResponse = fileService.getFile(id)
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
    fun updateFile(@RequestParam("fileId") id: String, @RequestBody updateFileRequest: UpdateFileRequest): WebResponse<FileResponse> {
        val itemResponse = fileService.updateFile(id, updateFileRequest)
        return WebResponse(
                code = 200,
                status = "OK",
                data = itemResponse
        )
    }

    @DeleteMapping(
            value = [""]
    )
    fun deleteFile(@RequestParam("fileId") id: String): WebResponse<String> {
        fileService.deleteFile(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = "file with id: $id is deleted"
        )
    }
}