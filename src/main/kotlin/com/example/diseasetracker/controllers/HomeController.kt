package com.example.diseasetracker.controllers

import com.example.diseasetracker.services.CoronaVirusDataService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class HomeController(private val service: CoronaVirusDataService) {
  @GetMapping("/")
  fun getAllStats() = service.getAllStats()
}
