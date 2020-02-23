package com.uni7.uni7quiz.domain

class QuestionService {

    fun getQuestions(): List<Question> {

        val optionsQuestion0 = listOf(Option(0, "5 dias"), Option(1, "8 dias"), Option(2, "9 dias"), Option(3, "10 dias"))
        val question0 = Question(0, "Uma lesma deve subir um poste de 10 metros de altura. De dia sobe 2m e à noite desce 1m. Em quantos dias atingirá o topo do poste?",-1, 2, optionsQuestion0, "https://static.quizur.com/i/b/59517ed9cda3c7.2581035759517ed9b1e375.44507165.jpg")

        val optionsQuestion1 = listOf(Option(0, "34"), Option(1, "16"), Option(2, "23"), Option(3, "16"))
        val question1 = Question(1, "Quantos quadrados você vê?",-1, 0, optionsQuestion1, "https://static.quizur.com/i/b/59517157c84024.1053851959517157ba4de0.66240643.jpg")

        val optionsQuestion2 = listOf(Option(0, "Nenhuma das respostas está correta"), Option(1, "Chile"), Option(2, "Que?"), Option(3, "Brasil"))
        val question2 = Question(2, "Uma pata nascida no Chile bota um ovo na divisa Brasil-Chile. Segundo o Itamaraty, a quem pertence o ovo?",-1, 0, optionsQuestion2, "https://static.quizur.com/i/b/59517f082e7de7.3118678459517f0811d231.84104442.jpg")

        val optionsQuestion3 = listOf(Option(0, "6 e 10"), Option(1, "1 e 2"), Option(2, "5 e 7"), Option(3, "4 e 8"))
        val question3 = Question(3, "Um pastor diz para outro: \"Dê um de seus carneiros que ficamos com igual número de carneiros.\" O outro responde:\n" +
                "\"Nada disso, dê-me um de seus carneiros que ficarei com o dobro dos seus\". Quantos carneiros têm cada um?",-1, 2, optionsQuestion3, "https://static.quizur.com/i/b/59517f8ddc91b6.6023922859517f8d7e2101.11106040.jpg")

        val optionsQuestion4 = listOf(Option(0, "31"), Option(1, "32"), Option(2, "28"), Option(3, "30"))
        val question4 = Question(4, "O professor Epaminondas, no primeiro dia de aula, apostou que, entre os alunos daquela classe, pelo menos dois fariam aniversário no mesmo dia do mês. O professor tinha certeza de que ganharia a aposta, pois naquela classe o número de alunos era maior ou igual a:",-1, 1, optionsQuestion4, "https://static.quizur.com/i/b/59517fcd6784c2.2112007359517fcd576d75.84643601.png")

        return listOf(question0, question1, question2, question3, question4)
    }
}