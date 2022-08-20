package com.bloxes.itemdb.helper

import org.springframework.stereotype.Component
import java.util.*

@Component
class UUIDHelper {
    fun getRandomUUID(): String {
        return UUID.randomUUID().toString()
    }
}