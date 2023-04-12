package com.example.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import com.example.workoutapp.databinding.ActivityBmiactivityBinding

class BMIActivity : AppCompatActivity() {

    private var binding : ActivityBmiactivityBinding ? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        setSupportActionBar(binding?.BMIToolbar)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "BMI CALCULATOR"
        }



        binding?.BMIToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Handle progress change here
                binding?.textView3?.text = "$progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Handle touch event when user starts dragging SeekBar here
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Handle touch event when user stops dragging SeekBar here
            }
        })

        binding?.CalculateButton?.setOnClickListener {
            if(validateUnits()){
                val heightValue : Float = binding?.height?.text.toString().toFloat()/100
                val weightValue : Float = binding?.Weight?.text.toString().toFloat()
                val bmi = weightValue/(heightValue*heightValue)
                val intent = Intent(this@BMIActivity,BMIResultActivity::class.java)
                intent.putExtra("Calculated BMI", bmi)
                startActivity(intent)

            }
            else{
                Toast.makeText(this@BMIActivity, "Please Enter Valid Values",Toast.LENGTH_SHORT).show()
            }
        }





    }
    private fun validateUnits():Boolean{
        var isValid = true
        if(binding?.height?.text.toString().isEmpty()){
            isValid = false
        }
        else if (binding?.Weight?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }


}