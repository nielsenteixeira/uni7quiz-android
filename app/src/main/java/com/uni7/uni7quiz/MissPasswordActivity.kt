package com.uni7.uni7quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.common.util.Strings
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_miss_password.*

class MissPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_miss_password)

        auth = FirebaseAuth.getInstance()

        val btnRecoverPassword = findViewById<Button>(R.id.btnRecoverPassword)
        val txtEmail = findViewById<EditText>(R.id.txtEmail)
        val txtVoltar = findViewById<TextView>(R.id.txtVoltar)

        btnRecoverPassword.setOnClickListener { view ->
            recoverPassword(view, txtEmail.text.toString())
        }

        txtVoltar.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun recoverPassword(view: View, email: String) {
        if(Strings.isEmptyOrWhitespace(email)) {
            Snackbar.make(view, "Informe seu e-mail.", Snackbar.LENGTH_LONG).setAction("Action", null).show()
        } else {
            btnRecoverPassword.isEnabled = false
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Snackbar.make(view, "Enviado instruções para seu e-mail.", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                } else {
                    Snackbar.make(view, "Error: ${task.exception?.message}", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }
                btnRecoverPassword.isEnabled = true
            }
            .addOnFailureListener { exception ->
                Snackbar.make(view, "Error: ${exception.message}", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                btnRecoverPassword.isEnabled = true
            }
        }
    }
}
