package com.m7mdra.notificationmanager.model

import android.service.notification.StatusBarNotification
import android.util.Log

fun StatusBarNotification.model(): PostedNotification {
    val notification = this.notification
    val extras = notification.extras
    extras.keySet().forEach {
        Log.d("MEGA", "model:$it:${extras[it]} ")
    }
    return PostedNotification(
        key = key, packageName = this.packageName,
        active = true,
        time = postTime,
        notificationId = 0,
        ticker = notification.tickerText?.toString() ?: "",
        title = extras.getString("android.title", ""),
        content = extras.getString("android.text", ""),
        channelId = notification.channelId,
    )
}