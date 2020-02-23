package com.uni7.uni7quiz.domain

class Question (val id: Long, val wording: String, val studentAnswer: Int, val answer: Int, val options: List<Option>, val imageLink: String)