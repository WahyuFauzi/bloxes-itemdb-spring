package com.bloxes.itemdb.service

import com.bloxes.itemdb.model.item.CreateItemRequest
import com.bloxes.itemdb.model.item.ItemResponse
import com.bloxes.itemdb.model.item.UpdateItemRequest

interface ItemService {
    fun createItem(createItemRequest: CreateItemRequest): ItemResponse

    fun getItem(id: String): ItemResponse

    fun updateItem(id: String, updateItemRequest: UpdateItemRequest): ItemResponse

    fun deleteItem(id: String)
}