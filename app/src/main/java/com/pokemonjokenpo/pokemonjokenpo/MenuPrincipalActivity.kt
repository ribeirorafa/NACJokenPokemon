package com.pokemonjokenpo.pokemonjokenpo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_principal.*

class MenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        btJogar.setOnClickListener{
            startActivity(Intent(this, GameActivity::class.java))
        }

        btRanking.setOnClickListener{
            startActivity(Intent(this,RankingActivity::class.java))
        }

        btSobre.setOnClickListener{
            startActivity(Intent(this,SobreActivity::class.java))
        }

        btSair.setOnClickListener{
            finish()
        }
    }
}
