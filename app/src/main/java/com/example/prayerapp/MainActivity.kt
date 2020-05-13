package com.example.prayerapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var player = MediaPlayer()
        val afd = assets.openFd("adhan.mp3")
        println(afd.length)
        player.setDataSource(afd.fileDescriptor)
        player.reset()
        player.setOnPreparedListener { player.start() }
        player.prepareAsync()
        player.start()
        icon.startAnimation(AnimationUtils.loadAnimation(this , R.anim.splash_in))
        Handler().postDelayed({
            icon.startAnimation(AnimationUtils.loadAnimation(this, R.anim.solash_out))
            Handler().postDelayed({
                icon.visibility = View.GONE
                startActivity(Intent(this , Dashboard::class.java ))
                finish()
            } , 500)



        },1500)


    }

}