package com.example.formulario

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConversorActivity : AppCompatActivity() {

    private val longitud = mapOf(
        "Metros" to 1.0,
        "Kilómetros" to 1000.0,
        "Centímetros" to 0.01,
        "Millas" to 1609.34,
        "Pies" to 0.3048
    )

    private val peso = mapOf(
        "Kilogramos" to 1.0,
        "Gramos" to 0.001,
        "Libras" to 0.453592,
        "Onzas" to 0.0283495
    )

    private lateinit var spinnerCategoria: Spinner
    private lateinit var spinnerOrigen: Spinner
    private lateinit var spinnerDestino: Spinner
    private lateinit var valorEntrada: EditText
    private lateinit var resultadoValor: TextView
    private lateinit var resultadoDetalle: TextView
    private lateinit var iconoResultado: ImageView

    private val categorias = listOf("Longitud", "Peso", "Temperatura")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)

        spinnerCategoria = findViewById(R.id.spinnerCategoria)
        spinnerOrigen = findViewById(R.id.spinnerOrigen)
        spinnerDestino = findViewById(R.id.spinnerDestino)
        valorEntrada = findViewById(R.id.valorEntrada)
        resultadoValor = findViewById(R.id.resultadoValor)
        resultadoDetalle = findViewById(R.id.resultadoDetalle)
        iconoResultado = findViewById(R.id.iconoResultado)

        spinnerCategoria.adapter = ArrayAdapter(
            this, android.R.layout.simple_spinner_dropdown_item, categorias
        )

        spinnerCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p: AdapterView<*>?, v: View?, pos: Int, id: Long) {
                actualizarUnidades(categorias[pos])
            }
            override fun onNothingSelected(p: AdapterView<*>?) {}
        }

        actualizarUnidades(categorias[0])

        findViewById<Button>(R.id.btnConvertir).setOnClickListener {
            convertir()
        }
    }

    private fun actualizarUnidades(categoria: String) {
        val unidades = when (categoria) {
            "Longitud" -> longitud.keys.toList()
            "Peso" -> peso.keys.toList()
            else -> listOf("Celsius", "Fahrenheit", "Kelvin")
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, unidades)
        spinnerOrigen.adapter = adapter
        spinnerDestino.adapter = adapter
    }

    private fun convertir() {
        val texto = valorEntrada.text.toString()
        if (texto.isEmpty()) {
            resultadoDetalle.text = "Ingrese un valor"
            return
        }

        val valor = texto.toDoubleOrNull()
        if (valor == null) {
            resultadoDetalle.text = "Valor inválido"
            return
        }

        val categoria = categorias[spinnerCategoria.selectedItemPosition]
        val origen = spinnerOrigen.selectedItem.toString()
        val destino = spinnerDestino.selectedItem.toString()

        val res = when (categoria) {
            "Longitud" -> {
                val base = valor * (longitud[origen] ?: 1.0)
                base / (longitud[destino] ?: 1.0)
            }
            "Peso" -> {
                val base = valor * (peso[origen] ?: 1.0)
                base / (peso[destino] ?: 1.0)
            }
            else -> convertirTemperatura(valor, origen, destino)
        }

        resultadoValor.text = "%.2f".format(res)
        resultadoDetalle.text = "%.2f %s = %.2f %s".format(valor, origen, res, destino)

        iconoResultado.setImageResource(
            when (categoria) {
                "Longitud" -> R.drawable.ic_conversor
                "Peso" -> R.drawable.ic_conversor
                else -> R.drawable.ic_conversor
            }
        )
    }

    private fun convertirTemperatura(valor: Double, origen: String, destino: String): Double {
        val celsius = when (origen) {
            "Fahrenheit" -> (valor - 32) * 5 / 9
            "Kelvin" -> valor - 273.15
            else -> valor
        }
        return when (destino) {
            "Fahrenheit" -> celsius * 9 / 5 + 32
            "Kelvin" -> celsius + 273.15
            else -> celsius
        }
    }
}