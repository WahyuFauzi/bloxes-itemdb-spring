package com.bloxes.itemdb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ItemdbApplication

fun main(args: Array<String>) {
	runApplication<ItemdbApplication>(*args)
}

//TODO implement error handling