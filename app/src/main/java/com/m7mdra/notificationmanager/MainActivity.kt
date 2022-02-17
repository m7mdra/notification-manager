package com.m7mdra.notificationmanager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.m7mdra.notificationmanager.databinding.ActivityMainBinding
import com.m7mdra.notificationmanager.db.AppDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object {
        private const val TAG = "MEGA"
    }

    private val database: AppDatabase by inject()

    private fun showPermissionDialog() {
        applicationContext.startActivity(
            Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS").addFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK
            )
        )
    }

    private fun showNotification() {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(
            NotificationChannel(
                "id123", "channel",
                NotificationManager.IMPORTANCE_HIGH
            )
        )

        val builder = NotificationCompat.Builder(this, "id123")
            .setContentTitle("Hello from me")
            .setContentText("Hello this is a test message")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_launcher_foreground
                )
            )

        notificationManager.notify(12, builder.build())
    }

    override fun onResume() {
        super.onResume()
        if (!NotificationManagerCompat.getEnabledListenerPackages(this).contains(packageName)) {
            binding.linearLayout.visibility = View.VISIBLE
        } else {
            binding.linearLayout.visibility = View.GONE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!NotificationManagerCompat.getEnabledListenerPackages(this).contains(packageName)) {
            binding.linearLayout.visibility = View.VISIBLE
            binding.enableButton.setOnClickListener {
                showPermissionDialog()
            }
        } else {
            binding.linearLayout.visibility = View.GONE
        }

        database.dao().getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe({
                binding.listView.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,it.map {
                    it.packageName+" " + it.content })
            }, {
                it.printStackTrace()
            })
    }
}