package com.pokemonjokenpo.pokemonjokenpo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.pokemonjokenpo.pokemonjokenpo.API.PokemonService
import com.pokemonjokenpo.pokemonjokenpo.model.Jogador
import kotlinx.android.synthetic.main.activity_game_over.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        //pontuacao
        val pontos = intent.extras.getInt("pontuacao")

val retrofit = Retrofit.Builder()
    .baseUrl("https://gamestjd.herokuapp.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

        btJogarNovamente.setOnClickListener{
            val servico = retrofit.create(PokemonService::class.java!!)
            servico.enviaPontuacao(pontuacao = Jogador(etNome.text.toString(),pontos))
                .enqueue(object : Callback<Void>{
                    override fun onFailure(call: Call<Void>?, t: Throwable?) {
                        Toast.makeText(this@GameOverActivity,
                            "Houve uma falha ao enviar",
                            Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<Void>?, response: Response<Void>?) {
                        Toast.makeText(this@GameOverActivity,
                            "Enviado com sucesso",
                            Toast.LENGTH_SHORT).show()
                    }

                })

            Handler().postDelayed({
                //Iniciando a tela de game
                val Game = Intent(this,GameActivity::class.java)
                startActivity(Game)
                //Finalizando/Destruindo esta minha tela
                finish()
            },2000)

        }

        btVoltarMenuPrincipal.setOnClickListener{
            startActivity(Intent(this,MenuPrincipalActivity::class.java))
        }

    }
}
