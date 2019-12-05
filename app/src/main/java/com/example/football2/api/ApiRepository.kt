package com.example.football2.api

import java.net.URL

class ApiRepository {
    fun doRequest(url: String): String {
        //extension functions readText untuk membaca url
        return URL(url).readText()
    }
}