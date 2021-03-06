package com.mvp.dagger.sample.main

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.mvp.dagger.sample.base.BaseActivity
import com.mvp.dagger.sample.R
import com.mvp.dagger.sample.photos.PhotosFragment
import com.mvp.dagger.sample.places.PlacesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, IMainContract.View {

    @Inject
    lateinit var presenter: IMainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbarTitle(R.string.app_name)
        initView()
        replaceFragment(PlacesFragment.newInstance(),R.id.main_content_view, PlacesFragment.TAG)
    }

    private fun initView() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        navView.menu.getItem(0).isChecked = true

        loadUserInfo()
    }

    private fun loadUserInfo() {
        val user = presenter.getUser()
        val textViewUserName = navView.getHeaderView(0).findViewById(R.id.tvUserName) as TextView
        val textViewUserEmail = navView.getHeaderView(0).findViewById(R.id.tvUserEmail) as TextView
        textViewUserName.text = user?.name
        textViewUserEmail.text = user?.email
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_places -> {
                replaceFragment(PlacesFragment.newInstance(),R.id.main_content_view, PlacesFragment.TAG)
            }
            R.id.nav_photos -> {
                replaceFragment(PhotosFragment.newInstance(),R.id.main_content_view, PhotosFragment.TAG)
            }
            R.id.nav_logout -> {
                presenter.logout()
                finishAffinity()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
