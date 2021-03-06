package com.example.prayerapp

import android.os.Build
import androidx.annotation.RequiresApi
import java.nio.channels.CancelledKeyException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import java.util.Date

class TimerPrayer() {
    companion object{
        var nextPrayer = 0
        var timeRemaining:Long = 0
        var nextAlarm :Calendar = Calendar.getInstance()
        var list = mutableListOf<Calendar>()

    }


    fun nextPrayer(): Int {
        var calendar = Calendar.getInstance()
//        val date = response.data.date.gregorian
//        calendar.set(date.year.toInt(),date.month.number,date.day.toInt())
        calendar.set(2020,5,13)
        println("calendar : $calendar")
        var minlist = mutableListOf<Boolean>()
        list.forEach{
            minlist.add(it.after(calendar))
            println("it : $it")
        }
        println("minlist : $minlist")
        nextPrayer = minlist.indexOf(true)
        if(nextPrayer == -1){
            updateList(0,"3:50",1)
            nextPrayer = 0
        }
        timeRemaining = list[nextPrayer].timeInMillis - calendar.timeInMillis
        nextAlarm = list[nextPrayer]
        return nextPrayer
    }

    fun getTimeRemaining(): Long {
        var calendar = Calendar.getInstance()
//        val date = response.data.date.gregorian
//        calendar.set(date.year.toInt(),date.month.number,date.day.toInt())
        calendar.set(2020,5,13)
        timeRemaining = nextAlarm.timeInMillis - calendar.timeInMillis
        return timeRemaining
    }
    fun setNextPrayer():Int{
        nextPrayer = (nextPrayer + 1).rem(5)
        return nextPrayer
    }

    fun addTolist(prayer:String,add:Int = 0){
        var time = prayer.split(":")
        val min = time[1].toInt()
        val hour = time[0].toInt()
//        val date = response.data.date.gregorian
        val calendar = Calendar.getInstance()
//        calendar.set(date.year.toInt(),date.month.number,date.day.toInt()+add,hour,min)
        calendar.set(2020,5,13,hour,min)
        list.add(calendar)
    }
    fun updateList(index:Int,prayer: String,add: Int=0){
        var time = prayer.split(":")
        val min = time[1].toInt()
        val hour = time[0].toInt()
//        val date = response.data.date.gregorian
        val calendar = Calendar.getInstance()
//        calendar.set(date.year.toInt(),date.month.number,date.day.toInt()+add,hour,min)
        calendar.set(2020,5,14,hour,min)
        list[index]=calendar
    }




}