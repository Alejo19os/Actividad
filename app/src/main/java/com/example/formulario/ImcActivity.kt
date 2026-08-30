package com.example.formulario

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ImcActivity : AppCompatActivity() {

    private lateinit var pesoInput: EditText
    private lateinit var alturaInput: EditText
    private lateinit var resultadoImc: TextView
    private lateinit var categoriaImc: TextView
    private lateinit var iconoImc: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)

        pesoInput = findViewById(R.id.peso)
        alturaInput = findViewById(R.id.altura)
        resultadoImc = findViewById(R.id.resultadoImc)
        categoriaImc = findViewById(R.id.categoriaImc)
        iconoImc = findViewById(R.id.iconoImc)

        findViewById<Button>(R.id.btnCalcular).setOnClickListener {
            calcularImc()
        }
    }

    private fun calcularImc() {
        val pesoTexto = pesoInput.text.toString()
        val alturaTexto = alturaInput.text.toString()

        if (pesoTexto.isEmpty() || alturaTexto.isEmpty()) {
            categoriaImc.text = "Complete ambos campos"
            resultadoImc.text = "0.0"
            return
        }

        val peso = pesoTexto.toDoubleOrNull()
        val altura = alturaTexto.toDoubleOrNull()

        if (peso == null || altura == null || altura <= 0.0) {
            categoriaImc.text = "Datos inválidos"
            resultadoImc.text = "0.0"
            return
        }

        val imc = peso / (altura * altura)
        resultadoImc.text = "%.1f".format(imc)

        categoriaImc.text = when {
            imc < 18.5 -> {
                iconoImc.setImageResource(R.drawable.ic_peso_bajo)
                "Bajo peso"
            }
            imc < 25.0 -> {
                iconoImc.setImageResource(R.drawable.ic_saludable)
                "Peso normal"
            }
            imc < 30.0 -> {
                iconoImc.setImageResource(R.drawable.ic_sobrepeso)
                "Sobrepeso"
            }
            else -> {
                iconoImc.setImageResource(R.drawable.ic_obesidad)
                "Obesidad"
            }
        }
    }
}