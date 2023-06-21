package com.example.chatgpt

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference


    private lateinit var userN: EditText
    private lateinit var password: EditText
    private lateinit var nombre: EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        auth = Firebase.auth

        userN = findViewById(R.id.txt_UserRead)
        password = findViewById(R.id.txt_PasswordRead)
        nombre = findViewById(R.id.txt_NameRead)

        val btnAtras = findViewById<Button>(R.id.btn_Atras)

        val btnRegistrar = findViewById<Button>(R.id.btn_GoRegister)

        btnRegistrar.setOnClickListener {
            if (userN.text.isNotEmpty() && password.text.isNotEmpty() && nombre.text.isNotEmpty()){
                createAccount(userN.text.toString(),password.text.toString(), nombre.text.toString())
            }
            else {
                Toast.makeText(this, "Los campos están vacios", Toast.LENGTH_SHORT).show()
            }
        }

        btnAtras.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount(userN: String, password:String, nombre:String){
        auth.createUserWithEmailAndPassword(userN,password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){

                    var user: FirebaseUser? = auth.currentUser
                    var userId:String = user!!.uid

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId)

                    var hashMap:HashMap<String,String> = HashMap()
                    hashMap.put("userId",userId)
                    hashMap.put("userName",nombre)
                    hashMap.put("profileImage","")

                    databaseReference.setValue(hashMap).addOnCompleteListener(this){
                        if (it.isSuccessful){
                            Toast.makeText(baseContext, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()
                            //mandar a la pantalla principal
                            val intent = Intent(this, Menu::class.java)
                            startActivity(intent)
                        }
                    }

                }
                else{
                    Toast.makeText(baseContext, "Algo salio mal, Error: "+task.exception, Toast.LENGTH_SHORT).show()
                }
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
}