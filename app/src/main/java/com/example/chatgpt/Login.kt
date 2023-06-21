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
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    companion object{
        lateinit var eEmail : String //variabla global
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val txt_User : TextView = findViewById(R.id.txt_UserRead)
        val txt_Pass : TextView = findViewById(R.id.txt_PasswordRead)

        val btn_Ingresar= findViewById<Button>(R.id.btn_Ingresar)
        val btn_Registrar= findViewById<TextView>(R.id.btn_Registrar)
        val btnExit = findViewById<Button>(R.id.btn_Salir)


        auth = Firebase.auth
        scheduleNotification()


        btn_Ingresar.setOnClickListener {

            if (txt_User.text.isNotEmpty() && txt_Pass.text.isNotEmpty()){
                signIn(txt_User.text.toString(),txt_Pass.text.toString())
            }
            else {
                Toast.makeText(this, "Los campos están vacios", Toast.LENGTH_SHORT).show()
            }
        }

        btn_Registrar.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            onDestroy()
            finishAffinity()
            System.exit(0)
        }

    }

    private fun signIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){ task ->
                if (task.isSuccessful){
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Autentifacion exitosa", Toast.LENGTH_SHORT).show()
                    eEmail = email //variable global
                    //mandar a la ventana de menu
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)

                }
                else{
                    Toast.makeText(baseContext, "Error de Email y/o Contraseña", Toast.LENGTH_SHORT).show()
                }
            }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        return
    }

    override fun onDestroy() {
        super.onDestroy()
        NotificationUtils.showNotification(this, "Yo estaré aquí por si me necesitas <3")
    }

    private fun scheduleNotification() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(this, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 10) // Establece la hora en la que se desea mostrar la notificación (9:00 AM)
            set(Calendar.MINUTE, 24)
        }
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }


}