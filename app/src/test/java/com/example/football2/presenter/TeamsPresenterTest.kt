package com.example.football2.presenter

import com.example.football2.api.ApiRepository
import com.example.football2.api.TestContextProvider
import com.example.football2.model.Team
import com.example.football2.model.TeamResponse
import com.example.football2.view.TeamsView
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamsPresenterTest {
    @Mock
    private lateinit var view: TeamsView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var  apiResponse: Deferred<String>

    //menginisialisasi semua mock object tersebut sebelum fungsi pengujian dijalankan,
    //Anda perlu menambahkan kode MockitoAnnotations.initMocks(this) di dalam fungsi setUp() yang memiliki anotasi
    // @Before.
    private lateinit var presenter: TeamsPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //enginisialisasikan presenter di dalam fungsi setUp().
        presenter = TeamsPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getTeamList() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"

        runBlocking {
            Mockito.`when`(apiRepository.doRequest(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            presenter.getTeamList(league)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(teams)
            Mockito.verify(view).hideLoading()
        }
    }
}