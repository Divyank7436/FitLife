package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workoutapp.databinding.ActivityBmiactivityBinding
import com.example.workoutapp.databinding.ActivityBmiresultBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIResultActivity : AppCompatActivity() {

    private var binding : ActivityBmiresultBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiresultBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.resultBMI)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "BMI CALCULATOR"
        }
        binding?.resultBMI?.setNavigationOnClickListener{
            onBackPressed()
        }

        var bmi = intent.getFloatExtra("Calculated BMI", 0.toFloat())
        displayBmiResult(bmi)

        binding?.btnRecalculate?.setOnClickListener {
            var intent = Intent(this@BMIResultActivity, BMIActivity::class.java)
            startActivity(intent)
        }

    }

    private fun displayBmiResult(bmi : Float){
        var bmiLabel : String = ""
        var bmiDescription: String = ""
        if(bmi <= 15){
            bmiLabel = "Very Severely Underweight"
            bmiDescription = "Oops! You really need to take care of yourself, Eat as much as possible"
        }else if(bmi > 15 && bmi<= 16){
            bmiLabel = " Severely Underweight"
            bmiDescription = "Oops! You really need to take care of yourself, Eat as much as possible"
        }else if(bmi> 16 && bmi <=18.5){
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take care of yourself, Eat as much as possible"
        }else if(bmi >18.5 && bmi<=25){
            bmiLabel = "Normal"
            bmiDescription = "Congratulation, You are in Good Shape!"
        }else if(bmi > 25 && bmi <=30){
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of yourself, Start doing Workout"
        }else if(bmi >30 && bmi<=35 ){
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of yourself, Start doing Workout"
        }else if(bmi > 35 && bmi<=40){
            bmiLabel = "Obese Class | (Severely obese)"
            bmiDescription = "OMG! You are in very bad condition! Act Now"
        }else if(bmi>40){
            bmiLabel = "Obese Class | (Very Severely obese)"
            bmiDescription = "OMG! You are in very bad condition! Act Now"
        }
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding?.bmiLabel?.text = bmiLabel
        binding?.bmiValue?.text = bmiValue
        binding?.bmiDescription?.text =  bmiDescription

    }
}