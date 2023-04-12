package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.os.postDelayed
import com.github.ybq.android.spinkit.SpinKitView

class StartingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starting)

        Handler().postDelayed({
            val spinKitView = findViewById<SpinKitView>(R.id.spin_kit)
            spinKitView.visibility = View.VISIBLE
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }, 5000)


    }
}