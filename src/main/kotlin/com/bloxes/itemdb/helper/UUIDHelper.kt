package com.bloxes.userdb.helper

import org.springframework.stereotype.Component
import java.util.*

@Component
class UUIDHelper {
    fun getRandomUUID(): String {
        return UUID.randomUUID().toString()
    }
}