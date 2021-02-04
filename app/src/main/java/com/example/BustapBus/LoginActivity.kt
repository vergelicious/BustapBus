package com.example.BustapBus

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var email:EditText
    lateinit var password: EditText
    lateinit var busLogin: Button
    lateinit var forgotPasword: Button
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.txtEmailBus)
        password = findViewById(R.id.txtPassBus)
        busLogin = findViewById(R.id.btnLoginBus)
        forgotPasword = findViewById(R.id.textViewForgotPassword)
        mAuth = FirebaseAuth.getInstance()
    }
}