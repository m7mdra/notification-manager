package com.m7mdra.notificationmanager.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.m7mdra.notificationmanager.model.PostedNotification
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface NotificationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(postedNotification: PostedNotification): Completable

    @Query("SELECT * from notification")
    fun getAll(): Flowable<List<PostedNotification>>



}