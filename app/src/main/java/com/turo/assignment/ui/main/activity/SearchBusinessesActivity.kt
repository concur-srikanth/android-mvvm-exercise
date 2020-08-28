package com.turo.assignment.ui.main.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.turo.assignment.R
import com.turo.assignment.ui.main.frag.SearchBusinessesFragment

class SearchBusinessesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchBusinessesFragment.newInstance())
                .commitNow()
        }

    }
}