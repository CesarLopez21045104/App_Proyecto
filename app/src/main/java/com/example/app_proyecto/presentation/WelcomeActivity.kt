package com.example.app_proyecto.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.app_proyecto.R

class WelcomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val startGameButton: Button = findViewById(R.id.start_game_button)
        startGameButton.setOnClickListener {
            // Navegar a MainActivity al hacer clic en "Comenzar Juego"
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Cierra WelcomeActivity para que no se pueda regresar
        }
    }
}
