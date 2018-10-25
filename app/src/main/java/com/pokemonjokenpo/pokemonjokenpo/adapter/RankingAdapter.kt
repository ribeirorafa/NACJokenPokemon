package com.pokemonjokenpo.pokemonjokenpo.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pokemonjokenpo.pokemonjokenpo.R
import com.pokemonjokenpo.pokemonjokenpo.model.Jogador
import kotlinx.android.synthetic.main.activity_game_over.view.*
import kotlinx.android.synthetic.main.activity_ranking_card.view.*
import java.security.AccessControlContext
import java.util.*

class RankingAdapter (private val players: List<Jogador>,
                      val context: Context,
                      val listener: (Jogador) -> Unit) : RecyclerView.Adapter<RankingAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jogadorPosition = players[position]
        holder?.let{
            it.bindView(jogadorPosition,listener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(context).inflate(
            R.layout.activity_ranking_card,parent,false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView (jogador : Jogador,
                      listener: (Jogador) -> Unit) = with(itemView){
            val etNome = tvNome
            val pontos = tvPontos

            etNome.text = jogador.nome
            tvPontos.text = jogador.pontos.toString()

            setOnClickListener{listener(jogador)}
        }
    }

}