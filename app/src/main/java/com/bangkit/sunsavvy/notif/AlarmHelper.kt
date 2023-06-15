package com.bangkit.sunsavvy.notif

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class AlarmHelper(private val context: Context) {

    fun setAlarms() {
        val alarmTimes = listOf("05:00", "07:00", "09:00", "11:00", "13:00", "15:00", "17:00", "19:00")

        for (time in alarmTimes) {
            val alarmTime = getTimeInMillis(time)
            val intent = Intent(context, AlarmReceiver::class.java)
            intent.putExtra("UV_INDEX", "xx") // Replace "xx" with the actual UV index value

            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            )

            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                alarmTime,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }
    }

    private fun getTimeInMillis(time: String): Long {
        val calendar = Calendar.getInstance()
        val splitTime = time.split(":")
        val hour = splitTime[0].toInt()
        val minute = splitTime[1].toInt()

        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        val currentTime = System.currentTimeMillis()

        if (calendar.timeInMillis <= currentTime) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        return calendar.timeInMillis
    }
}