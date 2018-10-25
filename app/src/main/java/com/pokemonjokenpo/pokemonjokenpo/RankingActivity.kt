package com.pokemonjokenpo.pokemonjokenpo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.pokemonjokenpo.pokemonjokenpo.API.PokemonService
import com.pokemonjokenpo.pokemonjokenpo.adapter.RankingAdapter
import com.pokemonjokenpo.pokemonjokenpo.model.Jogador
import kotlinx.android.synthetic.main.activity_ranking.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://gamestjd.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val servico = retrofit.create(PokemonService::class.java!!)

        servico.buscaRanking()
            .enqueue(object : Callback<List<Jogador>>
            {
                override fun onFailure(call: Call<List<Jogador>>, t: Throwable) {
                    Toast.makeText(this@RankingActivity,
                        "Houve uma falha ao enviar",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<Jogador>>?, response: Response<List<Jogador>>?) {
                    response?.body()?.let {
                        val players: List<Jogador> = it
                        configurarLista(players)
                    }
                }

            })
    }

    fun configurarLista(players: List<Jogador>){

        val rv = rvRanking

        rv.adapter = RankingAdapter(players,this,{
            Toast.makeText(this,"O player: " + it.nome + " tem um total de " + it.pontos + " pontos.",Toast.LENGTH_LONG).show()
        })

        rv.layoutManager = LinearLayoutManager(this)

    }

}
