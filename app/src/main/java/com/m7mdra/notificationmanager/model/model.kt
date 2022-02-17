package com.m7mdra.notificationmanager.model

import android.service.notification.StatusBarNotification

fun StatusBarNotification.model(): PostedNotification {
    val notification = this.notification
    notification.`when`
    val extras = notification.extras
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