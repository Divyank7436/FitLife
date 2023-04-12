package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapp.databinding.ActivityMainBinding
import com.smarteist.autoimageslider.SliderView

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.mainToolBar)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "FITLIFE"
        }

        val imageSlider = findViewById<SliderView>(R.id.imageSlider)
        val adapter = SliderAdapter(this)
        imageSlider.setSliderAdapter(adapter)

        binding?.startframe?.setOnClickListener{
            val intent = Intent(this@MainActivity, ExerciseActivity::class.java)

            startActivity(intent)

        }
        binding?.flBMI?.setOnClickListener{
            val intent = Intent(this@MainActivity, BMIActivity::class.java)

            startActivity(intent)

        }
        binding?.flHistory?.setOnClickListener{
            val intent = Intent(this@MainActivity, History::class.java)
            startActivity(intent)
        }

    }
}