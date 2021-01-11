package com.droid.authexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.droid.authexample.data.UserPreferences
import com.droid.authexample.ui.auth.AuthActivity
import com.droid.authexample.ui.auth.home.HomeActivity
import com.droid.authexample.ui.auth.startNewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)

        userPreferences.authToken.asLiveData().observe(this, Observer {
        val activity = if (it == null ) AuthActivity::class.java else HomeActivity::class.java
        startNewActivity(activity)
        })
    }
}