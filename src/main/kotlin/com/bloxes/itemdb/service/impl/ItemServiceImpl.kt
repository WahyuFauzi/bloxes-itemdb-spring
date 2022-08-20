package com.bloxes.itemdb.service.impl

import com.bloxes.itemdb.entity.Item
import com.bloxes.itemdb.helper.Helper
import com.bloxes.itemdb.model.item.CreateItemRequest
import com.bloxes.itemdb.model.item.ItemResponse
import com.bloxes.itemdb.model.item.UpdateItemRequest
import com.bloxes.itemdb.repository.ItemRepository
import com.bloxes.itemdb.service.ItemService
import com.bloxes.itemdb.helper.DateHelper
import com.bloxes.itemdb.helper.RepositoryHelperImpl
import com.bloxes.itemdb.helper.UUIDHelper
import kotlinx.coroutines.*
import org.springframework.stereotype.Service

@Service
class ItemServiceImpl(
    private val itemRepository: ItemRepository,
    private val dateHelper: DateHelper,
    private val repoHelper: RepositoryHelperImpl,
    private val uuidHelper: UUIDHelper,
    private val helper: Helper
) : ItemService {
    override fun createItem(createItemRequest: CreateItemRequest): ItemResponse {
        val item = Item(
            id = uuidHelper.getRandomUUID(),
            item_name = createItemRequest.item_name,
            item_total_size = createItemRequest.item_total_size,
            created_at = dateHelper.getCurrentDateInString(),
            updated_at = dateHelper.getCurrentDateInString()
        )
        itemRepository.save(item)
        return helper.itemToItemResponse(item)
    }

    override fun getItem(id: String): ItemResponse {
        val item = repoHelper.findItemByIdOrThrowNotFound(id)
        return helper.itemToItemResponse(item)
    }

    override fun updateItem(id: String, updateItemRequest: UpdateItemRequest): ItemResponse {
        val item = repoHelper.findItemByIdOrThrowNotFound(id)
        item.apply {
            item_name = updateItemRequest.item_name
            updated_at = dateHelper.getCurrentDateInString()
        }
        itemRepository.save(item)
        return helper.itemToItemResponse(item)
    }

    override fun deleteItem(id: String) {
        runBlocking {
            launch {
                itemRepository.deleteById(id)
            }
        }
    }
}