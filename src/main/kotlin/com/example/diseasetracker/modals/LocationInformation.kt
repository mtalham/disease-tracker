package com.example.diseasetracker.modals

class LocationInformation(
  val state: String,
  val country: String,
  val latestTotalCases: Int,
  val prevDayCases: Int
)

class AllStats(
  val stats: ArrayList<LocationInformation>,
  val totalReportedCases: Int,
  val totalNewCases: Int
)
