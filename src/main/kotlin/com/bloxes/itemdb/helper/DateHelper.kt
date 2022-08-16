package com.bloxes.userdb.helper

import org.springframework.stereotype.Component
import java.util.*

@Component
class DateHelper {
    fun getCurrentDateInString(): String {
        return Date().toString()
    }
}