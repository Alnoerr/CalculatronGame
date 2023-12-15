package com.xren.calculatorgame

// класс созданный специально для уровней
class Level(_start: Int, _target: Int, _moves: Int, vararg _ops: String) {
    var start = _start
    var target = _target
    var moves = _moves
    private var ops = _ops
    var table = Array(4) { Array(3) { "" } }

    init{
        table[0][0] = "OPT" // имя кнопки для настроек
        table[3][0] = "C" // имя кнопки для перезапуска уровня

        if("<<" in ops){
            table[0][2] = "<<" // имя кнопки для удаления последнего числа
        }
        if("| |" in ops){
            table[2][2] = "| |" // имя кнопки для взятия числа под модуль
        }

        for(op in _ops){
            if(op == "<<" || op == "| |") continue
            // цикл для операций +, -, *, /, % (эти операции распределяются случайно по клавиатуре)
            while(true){
                val x = (0..2).random()
                val y = (0..3).random()
                if(table[y][x] == ""){
                    table[y][x] = op
                    break
                }
            }
        }
    }
}

// массив объектов класса Level (новый уровень создаётся в одну строчку кода)
val levels = arrayOf(
    Level(0, 2, 2, "+1"),
    Level(1, 4, 2, "+1", "*2"),
    Level(1, 4, 2, "+1", "*3"),
    Level(1, 20, 3, "+1", "*4"),
    Level(5, 15, 3, "-1", "*4"),
    Level(30, 15, 4, "-9", "*5"),
    Level(16, 0, 3, "-4", "/2"),
    Level(54, 81, 3, "*3", "+9", "/3"),
    Level(2, 17, 2, "-1", "+16", "*3"),
    Level(69, 68, 4, "-19", "*2", "-42", "+6"),
    Level(777, 0,3,"<<"),
    Level(30, 15, 2, "-9", "*5", "<<"),
    Level(34, 4, 3,"<<", "*2", "-1"),
    Level(9, 16, 3, "<<", "*12", "+11", "*8"),
    Level(92, 48, 5, "*8", "-6", "<<", "*-1"),
)
