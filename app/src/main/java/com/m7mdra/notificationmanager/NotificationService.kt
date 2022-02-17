package com.m7mdra.notificationmanager

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.m7mdra.notificationmanager.db.AppDatabase
import com.m7mdra.notificationmanager.db.NotificationDao
import com.m7mdra.notificationmanager.model.PostedNotification
import com.m7mdra.notificationmanager.model.model
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject

class NotificationService : NotificationListenerService() {
    private val database: AppDatabase by inject()

    companion object {
        private const val TAG = "MEGA"
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if (sbn != null) {
            database.dao().add(sbn.model())
                .observeAndSubscribe()
        }
    }


    override fun onNotificationRemoved(
        sbn: StatusBarNotification?,
        rankingMap: RankingMap?,
        reason: Int
    ) {

    }
}

private fun Completable.observeAndSubscribe() {
    subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
}


