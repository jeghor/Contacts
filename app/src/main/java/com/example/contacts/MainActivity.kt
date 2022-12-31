package com.example.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.contacts.fragments.MainFragment
import com.example.contacts.models.AccountService


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = MainFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer,fragment)
            .commit()
    }
}

