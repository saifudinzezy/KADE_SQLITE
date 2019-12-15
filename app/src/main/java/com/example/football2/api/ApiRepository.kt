package com.example.football2.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class ApiRepository {
    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
        //extension functions readText untuk membaca url
        URL(url).readText()
    }
}