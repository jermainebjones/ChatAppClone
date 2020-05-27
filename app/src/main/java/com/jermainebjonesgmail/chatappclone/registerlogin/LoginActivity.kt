package com.jermainebjonesgmail.chatappclone.registerlogin


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.jermainebjonesgmail.chatappclone.R
import com.jermainebjonesgmail.chatappclone.messages.LatestMessagesActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        back_to_register_textview.setOnClickListener {

            // launch the login activity somehow
            val intent = Intent(this, RegisterActivity::class.java)
            finish()
        }



        login_button_login.setOnClickListener {
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            Log.d("Login", "Attempt login with email/pw: $email/***")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    // else if successful
                    Log.d("Main", "Successfully logged in with uid: ${it.result!!.user!!.uid}")
                    val intent = Intent(this, LatestMessagesActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Log.d("Main", "Failed to login: ${it.message}")
                    Toast.makeText(this, "Failed to log in: ${it.message}", Toast.LENGTH_SHORT).show()

                }


            }
        }

    }

