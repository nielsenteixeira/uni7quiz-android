package com.uni7.uni7quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val btnRegister = findViewById<Button>(R.id.btnRegister)

        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val txtPasswordConfirm = findViewById<EditText>(R.id.txtPasswordConfirm)
        val txtVoltar = findViewById<TextView>(R.id.txtVoltar)

        btnRegister.setOnClickListener{ view ->
            register(view, txtEmail.text.toString(), txtPassword.text.toString(), txtPasswordConfirm.text.toString())
        }

        txtVoltar.setOnClickListener{
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun register(view: View, email: String, password: String, confirmPassword: String) {
        if (!password.equals(confirmPassword)) {
            Snackbar.make(view, "Senhas não conferem!", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        } else {
            btnRegister.isEnabled = false
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(getBaseContext(), "Usuário cadastrado com sucesso! Efetue o login.", Toast.LENGTH_LONG).show()
                        goToLogin()
                    } else {
                        Snackbar.make(view, "Error: ${task.exception?.message}", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                    }

                    btnRegister.isEnabled = true
                }
        }
    }
}
