package com.bloxes.itemdb.repository

import com.bloxes.itemdb.entity.Folder
import org.springframework.data.mongodb.repository.MongoRepository

interface FolderRepository: MongoRepository<Folder, String> {
}