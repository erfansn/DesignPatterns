package creational

import kotlin.math.PI

sealed interface Shape {
    fun area(): Double
}

data class Circle(
    val center: Pair<Int, Int>,
    val radius: Int,
) : Shape {

    override fun area() = PI * radius * radius
}

data class Triangle(
    val height: Int,
    val base: Int,
) : Shape {

    override fun area() = base * height / 2.0
}

lateinit var shape: Shape

/** An object that supports cloning is called a prototype so `data class` in Kotlin uses prototype pattern. */
fun main() {
    val shape = when (val shape = shape) {
        is Circle -> {
            shape.copy(radius = shape.radius + 4)
        }
        is Triangle -> {
            shape.copy(base = 10)
        }
    }
    shape.area()
}
