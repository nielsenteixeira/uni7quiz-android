package com.uni7.uni7quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.squareup.picasso.Picasso
import com.uni7.uni7quiz.domain.Option
import com.uni7.uni7quiz.domain.Question
import com.uni7.uni7quiz.domain.QuestionService

class MainActivity : AppCompatActivity() {

    private lateinit var questionService: QuestionService
    private lateinit var answers: MutableList<Int>
    private var CURRENT_QUESTION: Int = 0
    private var TOTAL_QUESTIONS: Int = 0
    private var RIGHT_ANSWERS: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        answers = mutableListOf()
        questionService = QuestionService()
        val questions = questionService.getQuestions()
        TOTAL_QUESTIONS = questions.size

        buildQuestionOnScreen(questions[CURRENT_QUESTION])
    }

    private fun buildQuestionOnScreen(question: Question) {
        val txtWording = findViewById<TextView>(R.id.txtWording)
        val imgQuestion = findViewById<ImageView>(R.id.imgQuestion)

        txtWording.text = question.wording
        Picasso.get()
            .load(question.imageLink)
            .resize(160, 120)
            .into(imgQuestion)

        populateOptions(question, question.options)
    }

    private fun populateOptions(question: Question, options: List<Option>) {
        val listOptions = findViewById<ListView>(R.id.listOptions)
        val optionsText = options.map {
            option -> option.description
        }

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsText)
        listOptions.adapter = adapter
        listOptions.setOnItemClickListener { parent, view, position, id ->

            var toastMsg = "Resposta Errada!"

            if (position.equals(question.answer)) {
                toastMsg = "Resposta certa!"
                RIGHT_ANSWERS += 1
            }

            Toast.makeText(this@MainActivity, toastMsg,   Toast.LENGTH_SHORT).show()

            answers.add(CURRENT_QUESTION, position)

            if (CURRENT_QUESTION < TOTAL_QUESTIONS - 1) {
                CURRENT_QUESTION += 1
                buildQuestionOnScreen(questionService.getQuestions()[CURRENT_QUESTION])
            } else {
                redirectToResult()
            }
        }
    }

    private fun redirectToResult(){
        val resultIntent = Intent(this, ResultActivity::class.java)
        resultIntent.putExtra("SCORE", RIGHT_ANSWERS)
        resultIntent.putExtra("TOTAL_QUESTIONS", TOTAL_QUESTIONS)
        startActivity(resultIntent)
    }
}
