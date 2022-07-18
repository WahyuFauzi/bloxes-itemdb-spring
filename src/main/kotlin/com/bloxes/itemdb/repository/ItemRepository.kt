package com.bloxes.itemdb.repository

import com.bloxes.itemdb.entity.Item
import org.springframework.data.mongodb.repository.MongoRepository

interface ItemRepository: MongoRepository<Item, String> {
}