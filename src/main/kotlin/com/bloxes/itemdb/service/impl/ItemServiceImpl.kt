package com.bloxes.itemdb.service.impl

import com.bloxes.itemdb.entity.Item
import com.bloxes.itemdb.helper.Helper
import com.bloxes.itemdb.model.item.CreateItemRequest
import com.bloxes.itemdb.model.item.ItemResponse
import com.bloxes.itemdb.model.item.UpdateItemRequest
import com.bloxes.itemdb.repository.ItemRepository
import com.bloxes.itemdb.service.ItemService
import com.bloxes.itemdb.validator.ValidatorUtil
import org.springframework.stereotype.Service
import java.util.*

@Service
class ItemServiceImpl(private val itemRepository: ItemRepository, val helper: Helper): ItemService {
    override fun createItem(createItemRequest: CreateItemRequest): ItemResponse {
//        validator.validate(createItemRequest)
        val item = Item(
                id = UUID.randomUUID().toString(),
                item_name = createItemRequest.item_name,
                item_total_size = createItemRequest.item_total_size,
                created_at = Date(),
                updated_at = Date()
        )
        itemRepository.insert(item)
        return helper.itemToItemResponse(item)
    }

    override fun getItem(id: String): ItemResponse {
       val item = helper.itemOrNull(itemRepository.findById(id).get())
        return helper.itemToItemResponse(item)
    }

    override fun updateItem(id: String, updateItemRequest: UpdateItemRequest): ItemResponse {
//        validator.validate(updateItemRequest)
        val item = helper.itemOrNull(itemRepository.findById(id).get())
        item.apply {
            item_name = updateItemRequest.item_name
            item_total_size = updateItemRequest.item_total_size
            updated_at = Date()
        }
        itemRepository.save(item)
        return helper.itemToItemResponse(item)
    }

    override fun deleteItem(id: String) {
        itemRepository.deleteById(id)
    }
}