package com.example.formulario

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<CardView>(R.id.cardImc).setOnClickListener {
            startActivity(Intent(this, ImcActivity::class.java))
        }

        findViewById<CardView>(R.id.cardCalculadora).setOnClickListener {
            startActivity(Intent(this, CalculadoraActivity::class.java))
        }

        findViewById<CardView>(R.id.cardConversor).setOnClickListener {
            startActivity(Intent(this, ConversorActivity::class.java))
        }
    }
}