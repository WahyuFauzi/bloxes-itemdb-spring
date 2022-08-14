package com.bloxes.itemdb

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ItemdbApplication

fun main(args: Array<String>) {
	runApplication<ItemdbApplication>(*args)
}