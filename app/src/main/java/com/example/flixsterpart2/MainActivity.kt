package com.example.flixsterpart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flixsterpart2.R.id

/**
 * The MainActivity for the FlixsterPart2 app.
 * Launches a [PopularShow].
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(id.content, PopularShowFragment(), null).commit()
    }
}