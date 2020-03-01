package com.example.diseasetracker.services

import com.example.diseasetracker.modals.AllStats
import com.example.diseasetracker.modals.LocationInformation
import org.apache.commons.csv.CSVFormat
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.io.StringReader
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import javax.annotation.PostConstruct


@Service
class CoronaVirusDataService(
  private val DATA_URL: String = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv"
) {
  private val stats = ArrayList<LocationInformation>()

  fun getAllStats() =
    AllStats(stats, stats.sumBy { it.latestTotalCases }, stats.sumBy { it.latestTotalCases - it.prevDayCases })

  @PostConstruct
  @Scheduled(cron = "* * 1 * * *")
  fun fetchData() {
    val client = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder().uri(URI.create(DATA_URL)).build()
    val response: HttpResponse<String> = client.send(request, HttpResponse.BodyHandlers.ofString())

    CSVFormat.DEFAULT
      .withFirstRecordAsHeader()
      .parse(StringReader(response.body()))
      .forEach { record ->
        val state = record.get("Province/State")
        val latestCases: Int = record.get(record.size() - 1).toInt()
        val prevDayCases: Int = record.get(record.size() - 2).toInt()
        stats.add(LocationInformation(state, record.get("Country/Region"), latestCases, prevDayCases))
      }
  }
}
