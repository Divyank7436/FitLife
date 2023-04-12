package com.example.workoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.databinding.ActivityExerciseBinding
import com.example.workoutapp.databinding.CustomDialogBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding : ActivityExerciseBinding? = null
    private var tts: TextToSpeech? = null

    private var player: MediaPlayer? = null

    private var restTimer : CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer : CountDownTimer? = null
    private var exerciseProgress = 0
    private var currentExercisePosition = -1
    private var exerciseList : ArrayList<ExerciseModel>? = null
    private var exerciseAdapter : ExerciseStatusAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)

        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "EXERCISE"
        }



        binding?.toolbar?.setNavigationOnClickListener{
            //onBackPressed()
            customDialogForBackButton()
        }

        tts=TextToSpeech(this, this)

        exerciseList = Constants.defaultExerciseList()

        setRestProgressBar()
        setupExerciseStatusRecyclerView()

    }

    override fun onBackPressed() {
        customDialogForBackButton()
        //super.onBackPressed()
    }


    private fun customDialogForBackButton(){
        val customDialog  = Dialog(this)
        val dialogBinding = CustomDialogBackConfirmationBinding.inflate(layoutInflater)

        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)
        dialogBinding.btnYes.setOnClickListener{
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.btnNO.setOnClickListener{
            customDialog.dismiss()

        }
        customDialog.show()
    }

    private fun setupExerciseStatusRecyclerView(){
        binding?.rvExerciseStatus?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false )
        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!)
        binding?.rvExerciseStatus?.adapter = exerciseAdapter
    }

    private fun setupRestView(){

        try {
            val soundURI = Uri.parse("android.resource://com.example.workoutapp/"+R.raw.startsound)
            player = MediaPlayer.create(applicationContext, soundURI)
            player?.isLooping = false
            player?.start()
        }
        catch (e:Exception){
            e.printStackTrace()
        }

        binding?.flRestProgressbar?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExercise?.visibility = View.INVISIBLE
        binding?.flExerciseProgressBar?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility=View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility=View.VISIBLE



        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0

        }
        //currentExercisePosition++


        speakOut("Take a break and rest for 10 seconds,    Your next Exercise is "+ exerciseList!![currentExercisePosition+1].getName())

        binding?.tvUpcomingExerciseName?.text=exerciseList!![currentExercisePosition+1].getName()
        setRestProgressBar()
    }

    private fun setupExerciseView(){
        binding?.flRestProgressbar?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExercise?.visibility = View.VISIBLE
        binding?.flExerciseProgressBar?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.tvUpcomingLabel?.visibility=View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility=View.INVISIBLE

        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0

        }

        speakOut(exerciseList!![currentExercisePosition].getMethod())

        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExercise?.text=exerciseList!![currentExercisePosition].getName()

        setExerciseProgressBar()
    }


    private fun setRestProgressBar(){
        binding?.RestProgressBar?.progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.RestProgressBar?.progress = 10-restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++
                //Toast.makeText(this@ExerciseActivity, "Now we can start the Exercise", Toast.LENGTH_LONG).show()

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setupExerciseView()
            }

        }.start()

    }



    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(30000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress = 30-exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }
            override fun onFinish() {


                exerciseList!![currentExercisePosition].setIsSelected(false)
                exerciseList!![currentExercisePosition].setIsCompleted(true)
                exerciseAdapter!!.notifyDataSetChanged()

                if(currentExercisePosition < exerciseList?.size!!-1){
                    setupRestView()



                }


                else{

                    speakOut("Congratulation you have completed today's Exercise")

                    finish()

                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulation you have completed today's Exercise",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }.start()
    }



    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.ENGLISH)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "THE LANGUAGE SPECIFIED IS NOT SUPPORTED")
            } else {
                Log.e("TTS", "Initialisation Failed")
            }
        }
    }

     private  fun speakOut(text: String) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }







    override fun onDestroy() {
        super.onDestroy()

        if(restTimer != null){
            restTimer?.cancel()
            restProgress = 0

        }
        if(exerciseTimer != null){
            exerciseTimer?.cancel()
            exerciseProgress = 0

        }

        if(tts!= null){
            tts?.stop()
            tts?.shutdown()
        }

        binding = null
    }



}