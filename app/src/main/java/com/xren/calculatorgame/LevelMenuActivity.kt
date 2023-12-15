package com.xren.calculatorgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xren.calculatorgame.databinding.ActivityLevelMenuBinding

class LevelMenuActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLevelMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentLvl = intent.getIntExtra("lvl", 1)

        binding.apply{
            bCont.setOnClickListener {
                val intent = Intent(this@LevelMenuActivity, GameActivity::class.java)
                intent.putExtra("lvl", currentLvl)
                startActivity(intent)
            }
            bToMainMenu.setOnClickListener {
                val intent = Intent(this@LevelMenuActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
