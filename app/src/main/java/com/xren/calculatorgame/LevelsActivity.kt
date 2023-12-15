package com.xren.calculatorgame

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.xren.calculatorgame.databinding.ActivityLevelsBinding


class LevelsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLevelsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding = ActivityLevelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //сохранение внутренних настроек
        val save = getSharedPreferences("Save", MODE_PRIVATE)
        val maxLevel = save.getInt("level", 1)

        binding.apply{
            bToBack.setOnClickListener {
                val intent = Intent(this@LevelsActivity, MainActivity::class.java)
                startActivity(intent)
            }

            //создание таблицы из кнопок
            val levelButtons = Array(7) { Array(5) { bLvl1 } }
            levelButtons[0] = arrayOf(bLvl1, bLvl2, bLvl3, bLvl4, bLvl5)
            levelButtons[1] = arrayOf(bLvl6, bLvl7, bLvl8, bLvl9, bLvl10)
            levelButtons[2] = arrayOf(bLvl11, bLvl12, bLvl13, bLvl14, bLvl15)
            levelButtons[3] = arrayOf(bLvl16, bLvl17, bLvl18, bLvl19, bLvl20)
            levelButtons[4] = arrayOf(bLvl21, bLvl22, bLvl23, bLvl24, bLvl25)
            levelButtons[5] = arrayOf(bLvl26, bLvl27, bLvl28, bLvl29, bLvl30)
            levelButtons[6] = arrayOf(bLvl31, bLvl32, bLvl33, bLvl34, bLvl35)

            updateButtonTable(levelButtons, maxLevel)
        }
    }

    //обновление каждой кнопки в таблице
    private fun updateButtonTable(_levelButtons: Array<Array<Button>>, _maxLevel: Int){
        var num = 1
        for(row in _levelButtons){
            for(b in row){
                if(_maxLevel >= num){
                    setAvailableLevelButton(b)
                }
                else{
                    setBlockedLevelButton(b)
                }
                b.text = num.toString()
                num++
            }
        }
    }
    //кнопка доступного уровня
    private fun setAvailableLevelButton(_b: Button){
        _b.setBackgroundColor(resources.getColor(R.color.black, null))
        _b.setTextColor(resources.getColor(R.color.white, null))

        _b.setOnClickListener {
            val intent = Intent(this@LevelsActivity, GameActivity::class.java)
            intent.putExtra("lvl", _b.text.toString().toInt())
            startActivity(intent)
        }
    }
    //кнопка заблокированного уровня
    private fun setBlockedLevelButton(_b: Button){
        _b.setBackgroundColor(resources.getColor(R.color.maroon, null))
        _b.setTextColor(resources.getColor(R.color.black, null))
    }

}