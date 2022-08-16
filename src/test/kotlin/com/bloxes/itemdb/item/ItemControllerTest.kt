package com.bloxes.itemdb.item

import com.bloxes.itemdb.controller.ItemController
import com.bloxes.itemdb.model.WebResponse
import com.bloxes.itemdb.model.item.CreateItemRequest
import com.bloxes.itemdb.model.item.ItemResponse
import com.bloxes.itemdb.model.item.UpdateItemRequest
import com.bloxes.itemdb.service.ItemService
import com.bloxes.itemdb.service.impl.ItemServiceImpl
import com.bloxes.userdb.helper.DateHelper
import com.bloxes.userdb.helper.RepositoryHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class ItemControllerTest {

    @Mock
    val itemService: ItemService = mock(ItemService::class.java)

    private val itemController: ItemController = ItemController(itemService)

    private val id = "id"

    private val dummyCreateItemRequest: CreateItemRequest = CreateItemRequest(
        item_name = "joseph joestar",
        item_total_size = 69420
    )

    private val dummyUpdateItemRequest: UpdateItemRequest = UpdateItemRequest(
        item_name = "joseph joestar"
    )

    private val dummyItemResponse: ItemResponse = ItemResponse(
        id = "id",
        item_name = "joseph joestar",
        item_total_size = 69420,
        created_at = "24/12/1999",
        updated_at = "24/12/1999"
    )

    private val dummyWebResponse: WebResponse<ItemResponse> = WebResponse(
        code = 200,
        status = "OK",
        data = dummyItemResponse
    )

    private val dummyWebResponseString: WebResponse<String> = WebResponse(
        code = 200,
        status = "OK",
        data = "item with id: $id is deleted"
    )

    @Test
    fun initTest() {
        assertInstanceOf(ItemController::class.java, itemController)
    }

    @Test
    fun createItem() {
        `when`(itemService.createItem(dummyCreateItemRequest)).thenReturn(dummyItemResponse)
        val webResponse: WebResponse<ItemResponse> = itemController.createItem(dummyCreateItemRequest)
        verify(itemService).createItem(dummyCreateItemRequest)
        assertEquals(webResponse, dummyWebResponse)
    }

    @Test
    fun getItem() {
        `when`(itemService.getItem(id)).thenReturn(dummyItemResponse)
        val webResponse: WebResponse<ItemResponse> = itemController.getItem(id)
        verify(itemService).getItem(id)
        assertEquals(webResponse, dummyWebResponse)
    }

    @Test
    fun updateItem() {
        `when`(itemService.updateItem(id, dummyUpdateItemRequest)).thenReturn(dummyItemResponse)
        val webResponse: WebResponse<ItemResponse> = itemController.updateItem(id, dummyUpdateItemRequest)
        verify(itemService).updateItem(id, dummyUpdateItemRequest)
        assertEquals(webResponse, dummyWebResponse)
    }

    @Test
    fun deleteItem() {
        val webResponse: WebResponse<String> = itemController.deleteItem(id)
        verify(itemService).deleteItem(id)
        assertEquals(webResponse, dummyWebResponseString)
    }
}