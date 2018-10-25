package com.pokemonjokenpo.pokemonjokenpo.API

import retrofit2.Call
import com.pokemonjokenpo.pokemonjokenpo.model.Jogador
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PokemonService {
    @POST("jokenpokemon/pontuacao")
fun enviaPontuacao(@Body pontuacao: Jogador): Call<Void>
    @GET("jokenpokemon/pontuacao")
fun buscaRanking(): Call<List<Jogador>>
}