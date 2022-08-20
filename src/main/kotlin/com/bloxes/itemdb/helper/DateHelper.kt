package com.bloxes.itemdb.helper

import org.springframework.stereotype.Component
import java.util.*

@Component
class DateHelper {
    fun getCurrentDateInString(): String {
        return Date().toString()
    }
}