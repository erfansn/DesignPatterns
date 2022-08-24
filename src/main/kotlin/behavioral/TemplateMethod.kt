package behavioral

abstract class Pizza(
    private val makeDough: () -> Unit = {
        println("making 30cm dough")
    },
    private val applySauce: () -> Unit = {
        println("applying tomato sauce")
    },
    private val addIngredients: () -> Unit = {
        println("adding cheese")
    },
    private val bake: () -> Unit = {
        println("baking for 20 minutes")
    }
) {
    fun make() {
        makeDough()
        applySauce()
        addIngredients()
        bake()
    }
}

class Pepperoni : Pizza(
    addIngredients = {
        println("adding salami")
        println("adding onion")
        println("adding cheese")
    }
)

class BigPepperoni : Pizza(
    addIngredients = {
        println("adding salami")
        println("adding cheese")
    },
    makeDough = {
        println("making 50cm dough")
    }
)

/**
 * Pros:
 * - You can let clients override only certain parts of a large algorithm,
 * making them less affected by changes that happen to other parts of the algorithm.
 * - You can pull the duplicate code into a superclass.
 *
 * Cons:
 * - Some clients may be limited by the provided skeleton of an algorithm.
 * - You might violate the Liskov Substitution Principle by suppressing a default step implementation via a subclass.
 * - Template methods tend to be harder to maintain the more steps they have.
 */
fun main() {
    val pepperoni = Pepperoni()
    val bigPepperoni = BigPepperoni()

    pepperoni.make()
    println()
    bigPepperoni.make()
}
