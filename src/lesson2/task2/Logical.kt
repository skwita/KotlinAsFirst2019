@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import com.sun.org.apache.xpath.internal.operations.Bool
import lesson1.task1.sqr
import java.lang.Math.abs
import java.lang.Math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {

    val num1 = number / 1000
    val num2 = number / 100 % 10
    val num3 = number / 10 % 10
    val num4 = number % 10
    return num1 + num2 == num3 + num4
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    val diagonalThreat = abs(x1 - x2) == abs(y1 - y2)
    val parallelThreat = (x1 == x2) || (y1 == y2)
    return (diagonalThreat || parallelThreat)
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    fun leap(year: Int): Boolean = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)
    val isLeap = leap(year)
    if ((month == 2) && isLeap) {
        return (29)
    }
    return when (month) {
        1 -> 31
        2 -> 28
        3 -> 31
        4 -> 30
        5 -> 31
        6 -> 30
        7 -> 31
        8 -> 31
        9 -> 30
        10 -> 31
        11 -> 30
        12 -> 31
        else -> -1
    }


}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean = (r2 >= sqrt(sqr(x2 - x1) + sqr(y2 - y1)) + r1)

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean =
    when {
        ((a <= r) && (b <= s)) || ((a <= s) && (b <= r)) -> true
        ((c <= r) && (b <= s)) || ((c <= s) && (b <= r)) -> true
        ((a <= r) && (c <= s)) || ((a <= s) && (c <= r)) -> true
        else -> false
    }

