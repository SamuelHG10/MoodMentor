package com.example.chatgpt

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.util.*

class Menu : AppCompatActivity() {

    lateinit var btn_Ir : Button
    lateinit var btn_Salir : Button
    lateinit var btn_IrPsicologo : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        btn_Ir = findViewById(R.id.btn_IrChat)
        btn_Salir = findViewById(R.id.btn_Atras)
        btn_IrPsicologo = findViewById(R.id.btn_ChatParticular)

        scheduleNotification()

        btn_Ir.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_Salir.setOnClickListener {
            val intent1 = Intent(this, Login::class.java)
            startActivity(intent1)
        }

        btn_IrPsicologo.setOnClickListener {
            val intent1 = Intent(this, ChatPsicologo::class.java)
            startActivity(intent1)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        return
    }

    override fun onDestroy() {
        super.onDestroy()
        NotificationUtils.showNotification(this, "No te olvides de mi")
    }

    private fun scheduleNotification() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(this, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 10) // Establece la hora en la que se desea mostrar la notificación (9:00 AM)
            set(Calendar.MINUTE, 20)
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
}