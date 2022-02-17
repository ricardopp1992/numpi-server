package com.numpi.numpiserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NumpiServerApplication

fun main(args: Array<String>) {
	runApplication<NumpiServerApplication>(*args)
}
