package com.example.football2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.football2.R
import com.example.football2.view.fragment.FavoriteTeamsFragment
import com.example.football2.view.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //bootom navigation
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.teams -> {
                    loadTeamsFragment(savedInstanceState)
                }
                R.id.favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            true
        }

        bottom_navigation.selectedItemId = R.id.teams
    }

    //load fragment Team
    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        //cek apakah vie sdh diinstance/dibuat
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                .commit()
        }
    }

    //load fragment favortie
    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_container,
                    FavoriteTeamsFragment(),
                    FavoriteTeamsFragment::class.java.simpleName
                )
                .commit()
        }
    }
}
