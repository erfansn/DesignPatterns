package structural

import kotlin.math.sqrt

sealed interface Peg
data class RoundPeg(val radius: Double) : Peg
data class SquarePeg(val width: Double) : Peg

class RoundHole(private val radius: Double) {

    fun fits(roundPeg: RoundPeg): Boolean {
        return radius >= roundPeg.radius
    }
}

fun SquarePeg.toRoundPeg(): RoundPeg {
    return RoundPeg(width * sqrt(2.0) / 2.0)
}

/** In Kotlin we can use `Extension Function` to achieve the goal of using this pattern. */
fun main() {
    val roundHole = RoundHole(5.0)
    val squarePeg = SquarePeg(2.0)

    roundHole.fits(squarePeg.toRoundPeg())
}
