package com.xren.calculatorgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xren.calculatorgame.databinding.ActivityGuideBinding

class GuideActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply{
            bToBack.setOnClickListener {
                val intent = Intent(this@GuideActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
