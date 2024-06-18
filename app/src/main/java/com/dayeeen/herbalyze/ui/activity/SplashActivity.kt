package com.dayeeen.herbalyze.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.dayeeen.herbalyze.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Delay for 3 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            // Start the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close the splash activity
        }, 3000) // 3000 milliseconds delay
    }
}
