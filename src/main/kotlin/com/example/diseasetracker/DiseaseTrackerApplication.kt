package com.example.diseasetracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class DiseaseTrackerApplication

fun main(args: Array<String>) {
	runApplication<DiseaseTrackerApplication>(*args)
}
