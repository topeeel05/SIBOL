package com.example.sibol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // Declare DrawerLayout variable
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Initialize the DrawerLayout
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        // Set up the Toolbar
        val tb = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(tb)
        // Set up the NavigationView and its item click listener
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        // Set up the ActionBarDrawerToggle for handling drawer open/close events
        val toggle =
            ActionBarDrawerToggle(this, drawerLayout, tb, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Kung ano gusto fragment o page na magshow after login
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }
    }
    // mga item sa navigation drawer button
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            R.id.nav_sf -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SfFragment()).commit()
            R.id.nav_au -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AuFragment()).commit()
            R.id.nav_rate -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RatereviewFragment()).commit()
            R.id.nav_logout -> {
                showLogoutConfirmationDialog()
            }
        }
        // Close nav drawer after press button
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    // Show confirmation logout dialog yes or no
    private fun showLogoutConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Are you sure you want to logout?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        // Create and show the AlertDialog
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    override fun onBackPressed() {
        {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                onBackPressedDispatcher.onBackPressed()
            }
        }

    }
}