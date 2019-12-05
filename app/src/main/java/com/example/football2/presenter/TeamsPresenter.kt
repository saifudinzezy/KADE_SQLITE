package com.example.football2.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.api.TheSportDBApi
import com.example.football2.model.TeamResponse
import com.example.football2.view.TeamsView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson
) {
    fun getTeamList(league: String?) {
        //Ketika presenter sedang menunggu respon, ProgressBar akan ditampilkan.
        view.showLoading()
        //Fungsi doAsync dari Anko bisa digunakan untuk menjalankan asynchronous task. Ia akan memanggil fungsi doRequest,
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeams(league)),
                TeamResponse::class.java
            )

            //Ketika respon berhasil didapatkan, ProgressBar akan hilang dan data akan ditampilkan ke dalam view.
            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
}