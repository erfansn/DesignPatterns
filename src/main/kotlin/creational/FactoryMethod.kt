package creational

interface Transport

class Ship : Transport
class Truck : Transport

abstract class Logistics {
    abstract fun createTransport(): Transport

    fun deliver() {
        createTransport()
    }
}

class RoadLogistics : Logistics() {

    override fun createTransport() = Truck()
}

class SeaLogistics : Logistics() {

    override fun createTransport() = Ship()
}

enum class LogisticsType { ROAD, SEA }

lateinit var logisticsType: LogisticsType

/**
 * Pros:
 * - You avoid tight coupling between the creator and the concrete products.
 * - Single Responsibility Principle.
 * You can move the product creation code into one place in the program, making the code easier to support.
 * - Open/Closed Principle. You can introduce new types of products into the program without breaking existing client code.
 *
 * Cons:
 * - The code may become more complicated since you need to introduce a lot of new subclasses to implement the pattern.
 * The best case scenario is when you’re introducing the pattern into an existing hierarchy of creator classes.
 */
fun main() {
    val logistics = when (logisticsType) {
        LogisticsType.ROAD -> RoadLogistics()
        LogisticsType.SEA -> SeaLogistics()
    }

    logistics.deliver()
}
