package com.m7mdra.notificationmanager.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notification")
data class PostedNotification(
    @PrimaryKey
    val id: Int = Random().nextInt(),
    val key: String,
    val packageName: String,
    val active: Boolean,
    val time: Long,
    val notificationId: Int,
    val ticker: String,
    val title: String,
    val content: String,
    val channelId: String,

)

