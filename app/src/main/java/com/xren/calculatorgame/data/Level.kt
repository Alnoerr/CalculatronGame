package com.xren.calculatorgame.data

// класс созданный специально для уровней
data class Level(
    val id: Int,
    val start: Int,
    val target: Int,
    val moves: Int,
    val ops: List<String>
) {
    var table = Array(4) { Array(3) { "" } }

    init{
        table[0][0] = "OPT" // имя кнопки для настроек
        table[3][0] = "C" // имя кнопки для перезапуска уровня

        for(op in ops){
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
    Level(1, 0, 2, 2, listOf("+1")),
    Level(2, 1, 4, 2, listOf("+1", "*2")),
    Level(3, 1, 4, 2, listOf("+1", "*3")),
    Level(4, 1, 20, 3, listOf("+1", "*4")),
    Level(5, 5, 15, 3, listOf("-1", "*4")),
    Level(6, 30, 15, 4, listOf("-9", "*5")),
    Level(7, 16, 0, 3, listOf("-4", "/2")),
    Level(8, 54, 81, 3, listOf("*3", "+9", "/3")),
    Level(9, 2, 17, 2, listOf("-1", "+16", "*3")),
    Level(10, 69, 68, 4, listOf("-19", "*2", "-42", "+6")),
    Level(11, 777, 0,3,listOf("<<")),
    Level(12, 30, 15, 2, listOf("-9", "*5", "<<")),
    Level(13, 34, 4, 3,listOf("<<", "*2", "-1")),
    Level(14, 9, 16, 3, listOf("<<", "*12", "+11", "*8")),
    Level(15, 92, 48, 5, listOf("*8", "-6", "<<", "*-1"))
)
