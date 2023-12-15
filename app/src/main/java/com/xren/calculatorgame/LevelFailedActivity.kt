package com.xren.calculatorgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xren.calculatorgame.databinding.ActivityLevelFailedBinding

class LevelFailedActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLevelFailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelFailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentLvl = intent.getIntExtra("lvl", 1)

        binding.apply{
            bCont.setOnClickListener {
                val intent = Intent(this@LevelFailedActivity, GameActivity::class.java)
                intent.putExtra("lvl", currentLvl)
                startActivity(intent)
            }
            bToMainMenu.setOnClickListener {
                val intent = Intent(this@LevelFailedActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
