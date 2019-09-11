@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    when (age) {
        11, 12, 13, 14, 15, 16, 17, 18, 19, 111, 113, 114, 115, 116, 117, 118, 119 -> return ("$age лет") // exceptions
    }
    when (age % 10) {
        1 -> return ("$age год")
        2, 3, 4 -> return ("$age года")
        0, 5, 6, 7, 8, 9 -> return ("$age лет")
    }
    return ("error")
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val halfway: Double = (v1 * t1 + v2 * t2 + t3 * v3) / 2
    val dist1: Double = v1 * t1
    val dist2: Double = v2 * t2
    return when {
        dist1 >= halfway -> halfway / v1
        (dist2 + dist1) >= halfway -> (halfway - dist1) / v2 + t1
        else -> (halfway - dist1 - dist2) / v3 + t1 + t2
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    val rookThreat1: Boolean = (kingX == rookX1) or (kingY == rookY1)
    val rookThreat2: Boolean = (kingX == rookX2) or (kingY == rookY2)
    return when {
        rookThreat1 and rookThreat2 -> 3
        rookThreat2 -> 2
        rookThreat1 -> 1
        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val bishopThreat: Boolean =
        ((kingX - bishopX == kingY - bishopY) or (bishopX - kingX == kingY - bishopY) or
                (kingX - bishopX == bishopY - kingY) or (bishopX - kingX == bishopY - kingY))
    val rookThreat: Boolean = ((kingX == rookX) or (kingY == rookY))
    return when {
        bishopThreat and rookThreat -> (3)
        bishopThreat -> (2)
        rookThreat -> (1)
        else -> (0)
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
//    val angA = acos((b * b + c * c - a * a) / 2 * b * c)
//    val angB = acos((a * a + c * c - b * b) / 2 * a * c)
//    val angC = acos((b * b + a * a - c * c) / 2 * b * a)
//    return if ((angA + angB + angC) != PI) (-1)
//    else when {
//        (angA < PI / 2) and (angB < PI / 2) and (angC < PI / 2) -> 0
//        (angA == PI / 2) or (angB == PI / 2) or (angC == PI / 2) -> 1
//        (angA > PI / 2) or (angB > PI / 2) or (angC > PI / 2) -> 2
//        else -> -1


    var maxSide: Double = 0.0
    var sideOne: Double = 0.0
    var sideTwo: Double = 0.0
    when (max(a, max(b, c))) {
        a -> {
            maxSide = a
            sideOne = b
            sideTwo = c
        }
        b -> {
            maxSide = b
            sideOne = a
            sideTwo = c
        }
        c -> {
            maxSide = c
            sideOne = b
            sideTwo = a
        }
    }
    if (a <= b + c) return -1
    return when {
        (maxSide * maxSide == sideOne * sideOne + sideTwo * sideTwo) -> 1
        (maxSide * maxSide < sideOne * sideOne + sideTwo * sideTwo) -> 0
        (maxSide * maxSide > sideOne * sideOne + sideTwo * sideTwo) -> 2
        else -> -1
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        (a == d) or (b == c) -> 0
        (a == c) and (d > b) -> d - b
        (a == c) and (b > d) -> b - d
        ((a < c) and (b < d) and (b < c)) or ((c < a) and (d < b) and (d < a)) -> -1
        (c < b) and (d > b) -> b - c
        (a < d) and (b > d) -> d - a
        (a < c) and (a < d) -> d - c
        (c < a) and (c < b) -> a - d
        else -> -2
    }
}
