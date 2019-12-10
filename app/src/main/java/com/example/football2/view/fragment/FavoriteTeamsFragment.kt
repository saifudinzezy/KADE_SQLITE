package com.example.football2.view.fragment


import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dicoding.kotlinacademy.db.database
import com.example.football2.R
import com.example.football2.adapter.FavoriteTeamsAdapter
import com.example.football2.db.Favorite
import com.example.football2.view.detail.TeamDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.db.delete

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamsFragment : Fragment(), AnkoComponent<Context>{
    private var favorites: MutableList<Favorite> = mutableListOf()
    private lateinit var adapter: FavoriteTeamsAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var colorAccent = R.color.colorAccent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View  = with(ui){
        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                listTeam = recyclerView {
                    lparams (width = matchParent, height = wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
            //adapter
            adapter = FavoriteTeamsAdapter(favorites){
                context?.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
            }

            listTeam.adapter = adapter
            swipeRefresh.onRefresh {
                showFavorite()
            }
        }
    }

    //show db sqlite
    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            //yang selanjutnya dimasukkan ke dalam data class Favorite untuk ditampilkan pada RecyclerView oleh adapter
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged() //refresh db
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }
}
