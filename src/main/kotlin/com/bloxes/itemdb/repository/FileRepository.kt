package com.bloxes.itemdb.repository

import com.bloxes.itemdb.entity.File
import org.springframework.data.mongodb.repository.MongoRepository

interface FileRepository: MongoRepository<File, String> {
}