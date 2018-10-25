package com.pokemonjokenpo.pokemonjokenpo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashScreenPokemon : AppCompatActivity() {

    private val tempoSplash = 2000L

    lateinit var ivLogo : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_pokemon)

        ivLogo = findViewById(R.id.ivLogoPokemon)

        carregar()

    }

    fun carregar(){
        val animacao = AnimationUtils.loadAnimation(this,R.anim.animacao_pokemon_logo)
        animacao.reset()

        ivLogo!!.clearAnimation()
        ivLogo!!.startAnimation(animacao)

        Handler().postDelayed({

            val intent = Intent(this@SplashScreenPokemon, MenuPrincipalActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            this@SplashScreenPokemon.finish()

        }, tempoSplash)
    }

}
