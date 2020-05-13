package com.example.prayerapp

import com.google.gson.annotations.SerializedName

data class PrayerResponse(
    val code: Int,
    val `data`: Data,
    val status: String
)

data class Data(
    val date: Date,
    val meta: Meta,
    val timings: Timings
)

data class Date(
    val gregorian: Gregorian,
    val hijri: Hijri,
    val readable: String,
    val timestamp: String
)

data class Meta(
    val latitude: Double,
    val latitudeAdjustmentMethod: String,
    val longitude: Double,
    val method: Method,
    val midnightMode: String,
    val offset: Offset,
    val school: String,
    val timezone: String
)

data class Timings(
    @SerializedName("Asr")
    val asr: String,
    @SerializedName("Dhuhr")
    val dhuhr: String,
    @SerializedName("Fajr")
    val fajr: String,
    @SerializedName("Imsak")
    val imsak: String,
    @SerializedName("Isha")
    val isha: String,
    @SerializedName("Maghrib")
    val maghrib: String,
    @SerializedName("Midnight")
    val midnight: String,
    @SerializedName("Sunrise")
    val sunrise: String,
    @SerializedName("Sunset")
    val sunset: String
)

data class Gregorian(
    val date: String,
    val day: String,
    val designation: Designation,
    val format: String,
    val month: Month,
    val weekday: Weekday,
    val year: String
)

data class Hijri(
    val date: String,
    val day: String,
    val designation: DesignationX,
    val format: String,
    val holidays: List<Any>,
    val month: MonthX,
    val weekday: WeekdayX,
    val year: String
)

data class Designation(
    val abbreviated: String,
    val expanded: String
)

data class Month(
    val en: String,
    val number: Int
)

data class Weekday(
    val en: String
)

data class DesignationX(
    val abbreviated: String,
    val expanded: String
)

data class MonthX(
    val ar: String,
    val en: String,
    val number: Int
)

data class WeekdayX(
    val ar: String,
    val en: String
)

data class Method(
    val id: Int,
    val name: String,
    val params: Params
)

data class Offset(
    @SerializedName("Asr")
    val asr: Int,
    @SerializedName("Dhuhr")
    val dhuhr: Int,
    @SerializedName("Fajr")
    val fajr: Int,
    @SerializedName("Imsak")
    val imsak: Int,
    @SerializedName("Isha")
    val isha: Int,
    @SerializedName("Maghrib")
    val maghrib: Int,
    @SerializedName("Midnight")
    val midnight: Int,
    @SerializedName("Sunrise")
    val sunrise: Int,
    @SerializedName("Sunset")
    val sunset: Int
)

data class Params(
    @SerializedName("Fajr")
    val fajr: Int,
    @SerializedName("Isha")
    val isha: Int
)