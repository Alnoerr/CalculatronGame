package com.xren.calculatorgame

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xren.calculatorgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply{
            bStart.setOnClickListener {
                val intent = Intent(this@MainActivity, LevelsActivity::class.java)
                startActivity(intent)
            }
            bClearProgress.setOnClickListener {
                val save = getSharedPreferences("Save", MODE_PRIVATE)
                val editor: SharedPreferences.Editor = save.edit()
                editor.putInt("level", 1)
                editor.apply()
            }
            bGuide.setOnClickListener{
                val intent = Intent(this@MainActivity, GuideActivity::class.java)
                startActivity(intent)
            }
        }
    }
}