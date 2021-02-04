package com.example.BustapBus

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks
import java.util.concurrent.TimeUnit

class VerifyActivity : AppCompatActivity() {
    lateinit var inputCode1: EditText
    lateinit var inputCode2: EditText
    lateinit var inputCode3: EditText
    lateinit var inputCode4: EditText
    lateinit var inputCode5: EditText
    lateinit var inputCode6: EditText
    lateinit var verificationID: String
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)
        val textView = findViewById<TextView>(R.id.txtPhone)
        textView.text = String.format(
            "+63%s", intent.getStringExtra("number")
        )
        inputCode1 = findViewById(R.id.inputCode1)
        inputCode2 = findViewById(R.id.inputCode2)
        inputCode3 = findViewById(R.id.inputCode3)
        inputCode4 = findViewById(R.id.inputCode4)
        inputCode5 = findViewById(R.id.inputCode5)
        inputCode6 = findViewById(R.id.inputCode6)
        setupOTPInputs()
        val progressBar2 = findViewById<ProgressBar>(R.id.progressBar2)
        val buttonConfirm =
            findViewById<Button>(R.id.btnVerify)

        verificationID = intent.getStringExtra("verificationID").toString()
        buttonConfirm.setOnClickListener(View.OnClickListener {
            if (inputCode1.getText().toString().trim { it <= ' ' }.isEmpty()
                || inputCode2.getText().toString().trim { it <= ' ' }.isEmpty()
                || inputCode3.getText().toString().trim { it <= ' ' }.isEmpty()
                || inputCode4.getText().toString().trim { it <= ' ' }.isEmpty()
                || inputCode5.getText().toString().trim { it <= ' ' }.isEmpty()
                || inputCode6.getText().toString().trim { it <= ' ' }.isEmpty()
            ) {
                Toast.makeText(
                    this@VerifyActivity,
                    "Please enter a valid code.",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            val code = inputCode1.getText().toString() +
                    inputCode2.getText().toString() +
                    inputCode3.getText().toString() +
                    inputCode4.getText().toString() +
                    inputCode5.getText().toString() +
                    inputCode6.getText().toString()
            if (verificationID != null) {
                progressBar2.visibility = View.VISIBLE
                buttonConfirm.visibility = View.INVISIBLE
                val phoneAuthCredential =
                    PhoneAuthProvider.getCredential(verificationID!!, code)
                FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                    .addOnCompleteListener { task ->
                        progressBar2.visibility = View.GONE
                        buttonConfirm.visibility = View.VISIBLE
                        if (task.isSuccessful) {
                            val intent =
                                Intent(applicationContext, BusInfo::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@VerifyActivity,
                                "Verification code is invalid.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        })
        findViewById<View>(R.id.txtResendOTP).setOnClickListener {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+63" + intent.getStringExtra("number"),
                63,
                TimeUnit.SECONDS,
                this@VerifyActivity,
                object : OnVerificationStateChangedCallbacks() {
                    override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {}
                    override fun onVerificationFailed(e: FirebaseException) {
                        Toast.makeText(this@VerifyActivity, e.message, Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onCodeSent(
                        newVerificationID: String,
                        forceResendingToken: ForceResendingToken
                    ) {
                        verificationID = newVerificationID
                        Toast.makeText(this@VerifyActivity, "OTP Sent!", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )
        }
    }

    private fun setupOTPInputs() {
        inputCode1!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (!s.toString().trim { it <= ' ' }.isEmpty()) {
                    inputCode2!!.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        inputCode2!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (!s.toString().trim { it <= ' ' }.isEmpty()) {
                    inputCode3!!.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        inputCode3!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (!s.toString().trim { it <= ' ' }.isEmpty()) {
                    inputCode4!!.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        inputCode4!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (!s.toString().trim { it <= ' ' }.isEmpty()) {
                    inputCode5!!.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
        inputCode5!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (!s.toString().trim { it <= ' ' }.isEmpty()) {
                    inputCode6!!.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}