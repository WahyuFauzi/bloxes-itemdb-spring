package com.bloxes.itemdb.item

import com.bloxes.itemdb.entity.Item
import com.bloxes.itemdb.helper.Helper
import com.bloxes.itemdb.model.WebResponse
import com.bloxes.itemdb.model.item.CreateItemRequest
import com.bloxes.itemdb.model.item.ItemResponse
import com.bloxes.itemdb.model.item.UpdateItemRequest
import com.bloxes.itemdb.repository.ItemRepository
import com.bloxes.itemdb.service.impl.ItemServiceImpl
import com.bloxes.userdb.helper.DateHelper
import com.bloxes.userdb.helper.RepositoryHelper
import com.bloxes.userdb.helper.UUIDHelper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class ItemServiceTest {

    @Mock
    private val itemRepository = mock(ItemRepository::class.java)

    @Mock
    val dateHelper: DateHelper = mock(DateHelper::class.java)

    @Mock
    val repoHelper: RepositoryHelper = mock(RepositoryHelper::class.java)

    @Mock
    val uuidHelper: UUIDHelper = mock(UUIDHelper::class.java)

    private val helper: Helper = Helper()

    private val itemService: ItemServiceImpl = ItemServiceImpl(itemRepository, dateHelper, repoHelper, uuidHelper, helper)

    private val id = "id"

    private val item: Item = Item(
        id = "id",
        item_name = "joseph joestar",
        item_total_size = 69420,
        created_at = "24/12/1999",
        updated_at = "24/12/1999"
    )

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
        assertInstanceOf(ItemServiceImpl::class.java, itemService)
    }

    @Test
    fun createItem() {
        `when`(uuidHelper.getRandomUUID()).thenReturn(id)
        `when`(dateHelper.getCurrentDateInString()).thenReturn("24/12/1999")
        val itemResponse: ItemResponse = itemService.createItem(dummyCreateItemRequest)
        assertEquals(itemResponse, dummyItemResponse)
        verify(uuidHelper).getRandomUUID()
        verify(dateHelper, times(2)).getCurrentDateInString()
        verify(itemRepository).save(item)
    }

    @Test
    fun getItem() {
        `when`(repoHelper.findItemByIdOrThrowNotFound(id)).thenReturn(item)
        val itemResponse: ItemResponse = itemService.getItem(id)
        assertEquals(itemResponse, dummyItemResponse)
        verify(repoHelper).findItemByIdOrThrowNotFound(id)
    }

    @Test
    fun updateItem() {
        `when`(repoHelper.findItemByIdOrThrowNotFound(id)).thenReturn(item)
        `when`(dateHelper.getCurrentDateInString()).thenReturn("24/12/1999")
        val itemResponse: ItemResponse = itemService.updateItem(id, dummyUpdateItemRequest)
        assertEquals(itemResponse, dummyItemResponse)
        verify(repoHelper).findItemByIdOrThrowNotFound(id)
        verify(dateHelper).getCurrentDateInString()
    }
}