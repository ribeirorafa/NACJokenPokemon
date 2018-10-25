package com.pokemonjokenpo.pokemonjokenpo

import android.content.Intent
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {

    private var numeroAleatorio: Random?=null

    private val agua = 1
    private val grama = 2
    private val fogo = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        numeroAleatorio = Random()

        btSelecaoOpUm.setOnClickListener{
            ivPlayerSelecao!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.tentacruel))
            resultadoPartida(agua)
        }

        btSelecaoOpDois.setOnClickListener{
            ivPlayerSelecao!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.leafon))
            resultadoPartida(grama)
        }

        btSelecaoOpTres.setOnClickListener{
            ivPlayerSelecao!!.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.vulpix))
            resultadoPartida(fogo)
        }

    }

    private fun resultadoPartida(jogadaDoPlayer: Int){
        val jogadaPC = numeroAleatorio!!.nextInt(3) + 1

        when(jogadaPC){
            agua -> {
                ivCpuSelecao!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.tentacruel))
                when(jogadaDoPlayer){
                    agua -> empatou()
                    grama -> ganhou()
                    fogo -> perdeu()
                }
            }

            grama -> {
                ivCpuSelecao!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.leafon))
                when(jogadaDoPlayer){
                    agua -> perdeu()
                    grama -> empatou()
                    fogo -> ganhou()
                }
            }

            fogo -> {
                ivCpuSelecao!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.vulpix))
                when(jogadaDoPlayer){
                    agua -> ganhou()
                    grama -> perdeu()
                    fogo -> empatou()
                }
            }

        }
    }

    private var pontos = 0

    private fun ganhou(){

        pontos+=2
        tvVitoriaPontuacao.text = pontos.toString()
        tvResultadoFinal.setTextColor(ContextCompat.getColor(this,R.color.vitoria))
        tvResultadoFinal!!.text = "Player venceu!!"
    }

    private fun empatou(){
        pontos++
        tvEmpate.text = pontos.toString()
        tvResultadoFinal.setTextColor(ContextCompat.getColor(this,R.color.empate))
        tvResultadoFinal!!.text = "Houve um empate!!"
    }

    private fun perdeu(){
        tvResultadoFinal.setTextColor(ContextCompat.getColor(this,R.color.derrota))
        tvResultadoFinal!!.text = "Player perdeu!!"
        Handler().postDelayed({
            val telaGameOver = Intent(this,GameOverActivity::class.java)
            telaGameOver.putExtra("pontuacao", pontos)
            startActivity(telaGameOver)
            finish()
        },2000)
    }



}
