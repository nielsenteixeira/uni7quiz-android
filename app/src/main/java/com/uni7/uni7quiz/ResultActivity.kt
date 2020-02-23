package com.uni7.uni7quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class ResultActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        auth = FirebaseAuth.getInstance()

        val textScore = findViewById<TextView>(R.id.txtScore)
        val totalQuestions = findViewById<TextView>(R.id.txtTotal)

        textScore.text = intent.getIntExtra("SCORE", 0).toString()
        totalQuestions.text = intent.getIntExtra("TOTAL_QUESTIONS", 0).toString()


        val btnExit = findViewById<Button>(R.id.btnExit)
        btnExit.setOnClickListener() {
            auth.signOut()
            redirectToLogin()
        }

        var btnRestart = findViewById<Button>(R.id.btnRestart)
        btnRestart.setOnClickListener(){
            redirectToMain()
        }
    }

    private fun redirectToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun redirectToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
