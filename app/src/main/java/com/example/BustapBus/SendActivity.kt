package com.example.BustapBus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

class SendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)
        val inputNumber = findViewById<EditText>(R.id.inputNumber)
        val buttonGetOTP = findViewById<Button>(R.id.btnGetOTP)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        buttonGetOTP.setOnClickListener(View.OnClickListener {
            if (inputNumber.text.toString().trim { it <= ' ' }.isEmpty()) {
                Toast.makeText(this@SendActivity, "Enter Phone Number", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            progressBar.visibility = View.VISIBLE
            buttonGetOTP.visibility = View.INVISIBLE
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+63" + inputNumber.text.toString(),
                    63,
                    TimeUnit.SECONDS,
                    this@SendActivity,
                    object : OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                            progressBar.visibility = View.GONE
                            buttonGetOTP.visibility = View.INVISIBLE
                        }

                        override fun onVerificationFailed(e: FirebaseException) {
                            progressBar.visibility = View.GONE
                            buttonGetOTP.visibility = View.INVISIBLE
                            Toast.makeText(this@SendActivity, e.message, Toast.LENGTH_SHORT).show()
                        }

                        override fun onCodeSent(verificationID: String, forceResendingToken: ForceResendingToken) {
                            progressBar.visibility = View.GONE
                            buttonGetOTP.visibility = View.VISIBLE
                            val intent = Intent(applicationContext, VerifyActivity::class.java)
                            intent.putExtra("number", inputNumber.text.toString())
                            intent.putExtra("verificationID", verificationID)
                            startActivity(intent)
                        }
                    }
            )
        })
    }
}