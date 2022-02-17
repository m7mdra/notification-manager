package com.m7mdra.notificationmanager.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.m7mdra.notificationmanager.model.PostedNotification

@Database(entities = [PostedNotification::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): NotificationDao
}

