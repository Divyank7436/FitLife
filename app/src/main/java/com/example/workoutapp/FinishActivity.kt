package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.example.workoutapp.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FinishActivity : AppCompatActivity() {

    private var binding : ActivityFinishBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        setSupportActionBar(binding?.finishActivityToolBar)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "EXERCISE"
        }



        binding?.FinishButton?.setOnClickListener{
            finish()
        }
        val dao =(application as WorkoutApp).db.historyDao()
        addDateToDatabase(dao)


    }

    private fun addDateToDatabase(historyDao: HistoryDao){
        val c =  Calendar.getInstance()
        val dateTime = c.time
        Log.e("Date:" ,""+ dateTime)
       // val sdf = java.text.SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())
        val sdf = SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())

        val date = sdf.format(dateTime)
        Log.e("Formatted Date:", "" +date)
//
        lifecycleScope.launch{
            historyDao.insert(HistoryEntity(date))
            Log.e("Date :", "Added...")

        }


    }

}