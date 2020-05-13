package com.example.prayerapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.PeriodicWorkRequest
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val apiService = IApi()
        GlobalScope.launch(Dispatchers.Main) {
            val response = apiService.getPrayer("Paris", "France").await()
            Fajr.text = response.data.timings.fajr
            Dhuhr.text = response.data.timings.dhuhr
            Asr.text = response.data.timings.asr
            Maghrib.text = response.data.timings.maghrib
            Isha.text = response.data.timings.isha

            val timerPrayer = TimerPrayer()
            timerPrayer.addTolist(response.data.timings.fajr)
            timerPrayer.addTolist(response.data.timings.dhuhr)
            timerPrayer.addTolist(response.data.timings.asr)
            timerPrayer.addTolist(response.data.timings.maghrib)
            timerPrayer.addTolist(response.data.timings.isha)
            timerPrayer.nextPrayer()
            next.text = TimerPrayer.timeRemaining.toString()
        }
        val intent = Intent(this,PrayerService::class.java)
        startService(intent)
    }
}
