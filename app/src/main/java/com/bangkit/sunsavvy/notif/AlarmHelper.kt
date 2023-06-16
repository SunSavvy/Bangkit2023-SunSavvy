package com.bangkit.sunsavvy.notif

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class AlarmHelper(private val context: Context) {

    fun setAlarms() {
        val alarmTimes = listOf("05:30", "07:30", "09:30", "11:30", "13:30", "15:30", "17:30")
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        for (time in alarmTimes) {
            val alarmTime = getTimeInMillis(time)
            val intent = Intent(context, AlarmReceiver::class.java).apply {
                action = "ALARM_ACTION"
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE
            )
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
        val parts = time.split(":")
        val hour = parts[0].toInt()
        val minute = parts[1].toInt()
        calendar.apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }
        val currentTime = Calendar.getInstance().timeInMillis
        return if (calendar.timeInMillis <= currentTime) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            calendar.timeInMillis
        } else {
            calendar.timeInMillis
        }
    }
}
