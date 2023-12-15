package com.xren.calculatorgame

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xren.calculatorgame.databinding.ActivityGameBinding
import kotlin.math.abs


class GameActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGameBinding
    private var isLevelRestart = false
    private var isOptPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentLvl = intent.getIntExtra("lvl", 1) //значение выбранного уровня из LevelActivity

        binding.apply{
            //создание клавиатуры калькулятора
            val table = Array(4) { Array(3) { b1 } }
            table[0] = arrayOf(b1, b2, b3)
            table[1] = arrayOf(b4, b5, b6)
            table[2] = arrayOf(b7, b8, b9)
            table[3] = arrayOf(b10, b11, b12)

            tvCurrentLvl.text = currentLvl.toString()
            levelGenerator(levels[currentLvl - 1], table) //генерация текущего уровня
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun levelGenerator(_lvl: Level, _table: Array<Array<androidx.appcompat.widget.AppCompatButton>>){
        binding.apply{

            tvStart.text = _lvl.start.toString()
            tvTarget.text = _lvl.target.toString()
            tvMoves.text = _lvl.moves.toString()

            for(y in _table.indices){
                for(x in _table[y].indices){

                    _table[y][x].text = _lvl.table[y][x]
                    _table[y][x].isEnabled = true

                    when (_table[y][x].text) {
                        "" -> {
                            _table[y][x].isEnabled = false
                        }
                        "C" -> {
                            _table[y][x].background = getDrawable(R.drawable.calc_button_clear)
                            _table[y][x].textSize = 40F
                        }
                        "OPT" -> {
                            _table[y][x].background = getDrawable(R.drawable.calc_button_options)
                            _table[y][x].textSize = 0F
                        }
                        else -> {
                            _table[y][x].text = _lvl.table[y][x]
                        }
                    }

                    _table[y][x].setOnClickListener{
                        buttonOperation(_table[y][x].text.toString())
                        levelUpdate()
                    }
                }
            }
        }
    }

    //функция обновляющая уровень (запускается при нажатии на любую кнопку)
    private fun levelUpdate(){
        binding.apply{
            // условия обновления уровня
            if(tvStart.text == tvTarget.text){ // игрок дошёл до числа цели

                //сохранение уровня
                val currentLevel = tvCurrentLvl.text.toString().toInt() + 1
                val save = getSharedPreferences("Save", MODE_PRIVATE)
                val level = save.getInt("level", 1)
                if(level <= currentLevel - 1){
                    val editor: Editor = save.edit()
                    editor.putInt("level", currentLevel)
                    editor.apply()
                }

                //переход на активити с результатом уровня
                val intent = Intent(this@GameActivity, LevelPassedActivity::class.java)
                intent.putExtra("lvl", currentLevel)
                startActivity(intent)
            }

            else if(tvMoves.text == "0"){ // у игрока закончились ходы
                val currentLevel = tvCurrentLvl.text.toString().toInt()
                //переход на активити с результатом уровня
                val intent = Intent(this@GameActivity, LevelFailedActivity::class.java)
                intent.putExtra("lvl", currentLevel)
                startActivity(intent)
            }
            else if(isLevelRestart){ // игрок нажал на кнопку перезапуска уровня
                isLevelRestart = false

                val intent = Intent(this@GameActivity, GameActivity::class.java)
                intent.putExtra("lvl", tvCurrentLvl.text.toString().toInt())
                startActivity(intent)
            }
            else if(isOptPressed){ // игрок нажал на кнопку "настройки"
                isOptPressed = false

                val intent = Intent(this@GameActivity, LevelMenuActivity::class.java)
                intent.putExtra("lvl", tvCurrentLvl.text.toString().toInt())
                startActivity(intent)
            }
        }
    }

    private fun buttonOperation(opName: String){
        binding.apply{
            if(opName != ""){
                val op = opName[0]
                var res = tvStart.text.toString().toInt()
                if(op in "+-*/%") {
                    val num = opName.substring(1).toInt()
                    when(op){
                        '+' -> res += num
                        '-' -> res -= num
                        '*' -> res *= num
                        '/' -> res /= num
                        '%' -> res %= num
                    }
                    tvMoves.text = (tvMoves.text.toString().toInt() - 1).toString()
                }
                else if(op in "<|"){
                    when(op){
                        '<' -> res /= 10
                        '|' -> res = abs(res)
                    }
                    tvMoves.text = (tvMoves.text.toString().toInt() - 1).toString()
                }
                else if(op in "CO"){
                    when(op){
                        'C' -> isLevelRestart = true
                        'O' -> isOptPressed = true
                    }
                }
                tvStart.text = res.toString()
            }
        }
    }
}