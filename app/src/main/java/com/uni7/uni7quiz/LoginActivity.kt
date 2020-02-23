package com.uni7.uni7quiz

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val txtRegister = findViewById<TextView>(R.id.txtRegister)
        val txtMissPassword = findViewById<TextView>(R.id.txtMissPassword)

        btnLogin.setOnClickListener { view ->
            signIn(view, txtEmail.text.toString(), txtPassword.text.toString())
        }

        txtRegister.setOnClickListener{
            register()
        }

        txtMissPassword.setOnClickListener{
            missPassword()
        }

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            Toast.makeText(this, "You are already logged in!", Toast.LENGTH_LONG)
        }
    }

    fun signIn(view: View, email: String, password: String) {
        showMessage(view, "Authenticating...")

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    redirectToMainActivity()

                } else {
                    showMessage(view,"Error: ${task.exception?.message}")
                }
            }
    }

    private fun register(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun missPassword(){
        val intent = Intent(this, MissPasswordActivity::class.java)
        startActivity(intent)
    }

    private fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

    private fun redirectToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("id", auth.currentUser?.email)
        startActivity(intent)
    }
}