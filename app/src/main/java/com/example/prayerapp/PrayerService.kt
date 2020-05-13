package com.example.prayerapp

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.coroutines.*
import android.R
import android.app.*
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import androidx.core.app.NotificationCompat

import android.media.MediaPlayer
import androidx.core.app.NotificationManagerCompat




class PrayerService : Service() {
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder:Notification.Builder
    private val channelId = "com.example.prayerapp"
    private val description = "Test notification"


    override fun onBind(intent: Intent): IBinder?{
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()




        var builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_lock_idle_alarm)
            .setContentTitle("Prayer time")
            .setContentText("It's prayer time")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(Uri.parse("android.resource://$packageName/"))
//            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        val name = "chanel name"
        val descriptionText = description
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannel(channel)
        super.onCreate()
        GlobalScope.launch(Dispatchers.Main) {
            /*val apiService = IApi()
            val response = apiService.getPrayer("Paris", "France").await()*/
            val timerPrayer = TimerPrayer()
            TimerPrayer.list.clear()
            timerPrayer.addTolist("3:50")
            timerPrayer.addTolist("5:42")
            timerPrayer.addTolist("12:44")
            timerPrayer.addTolist("14:33")
            timerPrayer.addTolist("17:47")

            timerPrayer.nextPrayer()
            var last = false
            var delay:Long
            while(true){
                last = false
                 delay = timerPrayer.getTimeRemaining().minus(2000)
                while(timerPrayer.getTimeRemaining() > 0){
                    println("remaining time ... : "+ timerPrayer.getTimeRemaining())
                    if (!last){
                        Thread.sleep(delay)
                        last = true
                    }
                }

                with(NotificationManagerCompat.from(applicationContext)) {
                    notify(12345, builder.build())
                }

                timerPrayer.nextPrayer()

           }
        }
        }


    }

