package behavioral

object Strategy {

    fun add(n: Int, m: Int): Int {
        return n + m
    }

    fun sub(n: Int, m: Int): Int {
        return n - m
    }
}

class Context {
    var strategy = Strategy::add

    fun executeStrategy(n: Int, m: Int) = strategy(n, m)
}

/**
 * Pros:
 * - You can swap algorithms used inside an object at runtime.
 * - You can isolate the implementation details of an algorithm from the code that uses it.
 * - You can replace inheritance with composition.
 * - Open/Closed Principle. You can introduce new strategies without having to change the context.
 *
 * Cons:
 * - If you only have a couple of algorithms and they rarely change, there’s no real reason to overcomplicate the program
 * with new classes and interfaces that come along with the pattern.
 * - Clients must be aware of the differences between strategies to be able to select a proper one.
 * - A lot of modern programming languages have functional type support that lets you implement different versions of an algorithm inside a set of anonymous functions.
 * Then you could use these functions exactly as you’d have used the strategy objects, but without bloating your code with extra classes and interfaces.
 */
fun main() {
    val context = Context()
    context.strategy = Strategy::sub

    println(context.executeStrategy(1, 1))
}
