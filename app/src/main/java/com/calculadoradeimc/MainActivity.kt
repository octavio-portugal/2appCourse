package com.calculadoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var calcular = btn_calcular
        var deletar = ic_deletar

        calcular.setOnClickListener {
            var pesoVazio = edit_peso.text.toString()
            var alturaVazio = edit_altura.text.toString()

            if (pesoVazio.isEmpty()) {
                resultado.setText("Digite o seu Peso")
            } else if (alturaVazio.isEmpty()) {
                resultado.setText("Digite a sua Altura")
            } else {
                CalculoDeImc()
            }

        }

        deletar.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                edit_peso.setText("")
                edit_altura.setText("")
                resultado.setText("")
            }
            false
        }
    }

    private fun CalculoDeImc(){
        val peso = java.lang.Float.parseFloat(edit_peso.text.toString())
        val altura = java.lang.Float.parseFloat(edit_altura.text.toString())
        val resultado = resultado
        val imc = peso / (altura * altura)

        val mensagem = when {

            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau 1)"
            imc <= 39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Morbida (Grau 3)"
        }

        resultado.text = "IMC: " + imc.toString() + "\n" + mensagem

    }

}