package com.bloxes.itemdb.controller

import com.bloxes.itemdb.model.WebResponse
import com.bloxes.itemdb.model.item.CreateItemRequest
import com.bloxes.itemdb.model.item.ItemResponse
import com.bloxes.itemdb.model.item.UpdateItemRequest
import com.bloxes.itemdb.service.ItemService
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping(value = ["/api/v1/item"])
class ItemController(private val itemService: ItemService) {

    @PostMapping(
            value = [""],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun createItem(@RequestBody body: CreateItemRequest): WebResponse<ItemResponse> {
        val itemResponse = itemService.createItem(body)
        return WebResponse(
                code = 200,
                status = "OK",
                data = itemResponse
        )
    }

    @GetMapping(
            value = ["/{itemId}"],
            produces = ["application/json"]
    )
    fun getItem(@PathVariable("itemId") id: String): WebResponse<ItemResponse> {
        val itemResponse = itemService.getItem(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = itemResponse
        )
    }

    @PutMapping(
            value = ["/{itemId}"],
            produces = ["application/json"],
            consumes = ["application/json"]
    )
    fun updateItem(@PathVariable("itemId") id: String, @RequestBody updateItemRequest: UpdateItemRequest): WebResponse<ItemResponse> {
        val itemResponse = itemService.updateItem(id, updateItemRequest)
        return WebResponse(
                code = 200,
                status = "OK",
                data = itemResponse
        )
    }

    @DeleteMapping(
            value = ["/{itemId}"]
    )
    fun deleteItem(@PathVariable("itemId") id: String): WebResponse<String> {
        itemService.deleteItem(id)
        return WebResponse(
                code = 200,
                status = "OK",
                data = "item with id: $id is deleted"
        )

    }
}