package com.xren.calculatorgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xren.calculatorgame.databinding.ActivityLevelPassedBinding

class LevelPassedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLevelPassedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelPassedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentLvl = intent.getIntExtra("lvl", 1)

        binding.apply{
            bCont.setOnClickListener {
                val intent = Intent(this@LevelPassedActivity, GameActivity::class.java)
                intent.putExtra("lvl", currentLvl)
                startActivity(intent)
            }
            bToMainMenu.setOnClickListener {
                val intent = Intent(this@LevelPassedActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
