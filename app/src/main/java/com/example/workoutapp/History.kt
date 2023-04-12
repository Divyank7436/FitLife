package com.example.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.databinding.ActivityHistoryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class History : AppCompatActivity() {

    private var binding : ActivityHistoryBinding ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        setSupportActionBar(binding?.historyToolbar)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"
        }
        binding?.historyToolbar?.setNavigationOnClickListener{
            onBackPressed()
        }

        val dao =(application as WorkoutApp).db.historyDao()
         getAllCompletedDates(dao)
    }
    private fun getAllCompletedDates(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect{
                allCompletedDatesList ->
               if(allCompletedDatesList.isNotEmpty()){
                   binding?.textView?.visibility = View.VISIBLE
                   binding?.rvHistory?.visibility = View.VISIBLE
                   binding?.notDataAvailable?.visibility = View.GONE

                   binding?.rvHistory?.layoutManager = LinearLayoutManager(this@History)
                   val dates = ArrayList<String>()
                   for(date in allCompletedDatesList){
                       dates.add(date.date)

                   }
                   val historyAdapter = HistoryAdapter(ArrayList(dates))
                   binding?.rvHistory?.adapter = historyAdapter


                }
                else{
                   binding?.textView?.visibility = View.GONE
                   binding?.rvHistory?.visibility = View.GONE
                   binding?.notDataAvailable?.visibility = View.VISIBLE
               }
            }
        }
    }
}