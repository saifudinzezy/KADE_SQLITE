package com.example.football2.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.api.TheSportDBApi
import com.example.football2.model.TeamResponse
import com.example.football2.view.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamsPresenter(
    private val view: TeamsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getTeamList(league: String?) {
        //Ketika presenter sedang menunggu respon, ProgressBar akan ditampilkan.
        view.showLoading()
        //Fungsi doAsync dari Anko bisa digunakan untuk menjalankan asynchronous task. Ia akan memanggil fungsi doRequest,
        CoroutineScope(Dispatchers.Main).launch  {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeams(league)).await(),
                TeamResponse::class.java
            )

            //Ketika respon berhasil didapatkan, ProgressBar akan hilang dan data akan ditampilkan ke dalam view.
            view.showTeamList(data.teams)
            view.hideLoading()
        }
    }
}