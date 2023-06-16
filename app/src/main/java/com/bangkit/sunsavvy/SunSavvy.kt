package com.bangkit.sunsavvy

import android.app.Application
import com.bangkit.sunsavvy.notif.AlarmHelper

class SunSavvy : Application() {
    override fun onCreate() {
        super.onCreate()

        val alarmHelper = AlarmHelper(this)
        alarmHelper.setAlarms()
    }
}
