package com.example.app_proyecto.presentation

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.app_proyecto.R

class MainActivity : ComponentActivity() {

    private val questions = listOf(
        "¿Cuál es la capital de Francia?",
        "¿Cuál es el idioma oficial de Brasil?",
        "¿Cuántos continentes hay en el mundo?",
        "¿Cuál es el planeta más cercano al Sol?",
        "¿Cuál es el océano más grande?"
    )

    private val answers = listOf(
        listOf("París", "Roma", "Berlín"),
        listOf("Portugués", "Español", "Inglés"),
        listOf("5", "6", "7"),
        listOf("Venus", "Mercurio", "Tierra"),
        listOf("Atlántico", "Pacífico", "Índico")
    )

    private val correctAnswers = listOf(0, 0, 2, 1, 1)
    private var currentQuestion = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showQuestion()

        val restartButton: Button = findViewById(R.id.restart_button)
        restartButton.setOnClickListener {
            // Cargar la animación de explosión
            val explodeAnimation = AnimationUtils.loadAnimation(this, R.anim.explode_animation)
            restartButton.startAnimation(explodeAnimation)

            // Iniciar el juego al terminar la animación
            explodeAnimation.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation?) {}
                override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                    restartGame()
                }
                override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
            })
        }
    }

    private fun showQuestion() {
        val questionText: TextView = findViewById(R.id.question_text)
        val answerButton1: Button = findViewById(R.id.answer_button1)
        val answerButton2: Button = findViewById(R.id.answer_button2)
        val answerButton3: Button = findViewById(R.id.answer_button3)
        val resultText: TextView = findViewById(R.id.result_text)
        val restartButton: Button = findViewById(R.id.restart_button)

        if (currentQuestion < questions.size) {
            questionText.text = questions[currentQuestion]
            answerButton1.text = answers[currentQuestion][0]
            answerButton2.text = answers[currentQuestion][1]
            answerButton3.text = answers[currentQuestion][2]

            answerButton1.setOnClickListener { checkAnswer(0, resultText) }
            answerButton2.setOnClickListener { checkAnswer(1, resultText) }
            answerButton3.setOnClickListener { checkAnswer(2, resultText) }
        } else {
            questionText.text = "¡Juego terminado! Puntuación: $score/${questions.size}"
            answerButton1.visibility = Button.GONE
            answerButton2.visibility = Button.GONE
            answerButton3.visibility = Button.GONE
            resultText.text = ""
            restartButton.visibility = Button.VISIBLE
        }
    }

    private fun checkAnswer(answerIndex: Int, resultText: TextView) {
        if (answerIndex == correctAnswers[currentQuestion]) {
            score++
            resultText.text = "¡Correcto!"
        } else {
            resultText.text = "Incorrecto"
        }
        currentQuestion++
        showQuestion()
    }

    private fun restartGame() {
        currentQuestion = 0
        score = 0
        findViewById<Button>(R.id.answer_button1).visibility = Button.VISIBLE
        findViewById<Button>(R.id.answer_button2).visibility = Button.VISIBLE
        findViewById<Button>(R.id.answer_button3).visibility = Button.VISIBLE
        findViewById<Button>(R.id.restart_button).visibility = Button.GONE
        showQuestion()
    }
}