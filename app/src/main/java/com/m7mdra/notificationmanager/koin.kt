package com.m7mdra.notificationmanager

import androidx.room.Room
import com.m7mdra.notificationmanager.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mainModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "noti")
            .enableMultiInstanceInvalidation()
            .build()

    }
}