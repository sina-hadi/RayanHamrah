package com.codinginflow.rayanhamrah.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.forEach
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.codinginflow.rayanhamrah.R
import com.codinginflow.rayanhamrah.ui.bottomsheet.LogInDialogFragment
import com.codinginflow.rayanhamrah.ui.custom.CustomEthernet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var toggle: ActionBarDrawerToggle

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val farsi = Locale("fa", "IR")
        resources.configuration.setLocale(farsi)

        var stockNav = false

        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navController = findNavController(R.id.navHostFragment)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.sina -> {
                    val bottomSheet = LogInDialogFragment()
                    bottomSheet.setStyle(
                        BottomSheetDialogFragment.STYLE_NORMAL,
                        R.style.AppBottomSheetDialogTheme
                    )
                    bottomSheet.show(supportFragmentManager, "TAG")
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_search -> {
                    navController.setGraph(R.navigation.stock_nav)
                    stockNav = true
                    bottomNavigation.menu.forEach { it.isCheckable = false }
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_portfo -> {
                    findViewById<CustomEthernet>(R.id.ethernet).leftColor = R.color.teal_200
                }
                R.id.nav_eye -> {
                    findViewById<CustomEthernet>(R.id.ethernet).rightColor = R.color.yellow
                }
            }
            true
        }

        bottomNavigation.menu.forEach { menu ->
            menu.setOnMenuItemClickListener {
                if (stockNav) {
                    navController.setGraph(R.navigation.main_nav)
                    bottomNavigation.menu.forEach { menu2 ->
                        menu2.isCheckable = true
                    }
                    Log.e("ABCD", "${menu.itemId}")
                    stockNav = false
                    bottomNavigation.selectedItemId = menu.itemId
                    return@setOnMenuItemClickListener true
                } else {
                    return@setOnMenuItemClickListener false
                }
            }
        }

        bottomNavigation.setupWithNavController(
            navController
        )

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_drawer)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}