package io.microsamples.accelerator.chachkies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["io.microsamples.accelerator.chachkies", "io.microsamples.accelerator.chachkies.api"])
class ChachkiesApiApplication

fun main(args: Array<String>) {
	runApplication<ChachkiesApiApplication>(*args)
}
