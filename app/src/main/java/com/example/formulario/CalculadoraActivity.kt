package com.example.formulario

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var numero1: EditText
    private lateinit var numero2: EditText
    private lateinit var pantalla: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        numero1 = findViewById(R.id.numero1)
        numero2 = findViewById(R.id.numero2)
        pantalla = findViewById(R.id.pantalla)

        findViewById<Button>(R.id.btnSumar).setOnClickListener { operar('+') }
        findViewById<Button>(R.id.btnRestar).setOnClickListener { operar('-') }
        findViewById<Button>(R.id.btnMultiplicar).setOnClickListener { operar('*') }
        findViewById<Button>(R.id.btnDividir).setOnClickListener { operar('/') }
    }

    private fun operar(op: Char) {
        val n1 = numero1.text.toString().toDoubleOrNull()
        val n2 = numero2.text.toString().toDoubleOrNull()

        if (n1 == null || n2 == null) {
            pantalla.text = "Datos inválidos"
            return
        }

        val resultado = when (op) {
            '+' -> n1 + n2
            '-' -> n1 - n2
            '*' -> n1 * n2
            '/' -> {
                if (n2 == 0.0) {
                    pantalla.text = "Error: div/0"
                    return
                }
                n1 / n2
            }
            else -> 0.0
        }

        pantalla.text = "%.2f".format(resultado)
    }
}