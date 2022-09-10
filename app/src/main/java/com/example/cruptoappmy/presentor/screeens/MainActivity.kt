package com.example.cruptoappmy.presentor.screeens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cruptoappmy.R
import com.example.cruptoappmy.presentor.screeens.FirstFragment.Companion.FIRST_FRAGMENT_NAME

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_container_first, FirstFragment.getFragment())
            .commit()
    }


}