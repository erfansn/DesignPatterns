package creational

interface Chair
interface Sofa
interface CoffeeTable

interface FurnitureFactory {
    fun createChair(): Chair
    fun createSofa(): Sofa
    fun createCoffeeTable(): CoffeeTable
}

class ModernChair : Chair
class ModernSofa : Sofa
class ModernCoffeeTable : CoffeeTable

class ClassicChair : Chair
class ClassicSofa : Sofa
class ClassicCoffeeTable : CoffeeTable

object ModernFurnitureFactory : FurnitureFactory {
    override fun createChair() = ModernChair()

    override fun createSofa() = ModernSofa()

    override fun createCoffeeTable() = ModernCoffeeTable()
}

object ClassicFurnitureFactory : FurnitureFactory {
    override fun createChair() = ClassicChair()

    override fun createSofa() = ClassicSofa()

    override fun createCoffeeTable() = ClassicCoffeeTable()
}

class FurnitureShop(private val furnitureFactory: FurnitureFactory) {

    fun orderSofa() = furnitureFactory.createSofa()
}

enum class FurnitureType { MODERN, CLASSIC }

lateinit var furnitureType: FurnitureType

/**
 * Pros:
 * - You can be sure that the products you’re getting from a factory are compatible with each other.
 * - You avoid tight coupling between concrete products and client code.
 * - Single Responsibility Principle. You can extract the product creation code into one place, making the code easier to support.
 * - Open/Closed Principle. You can introduce new variants of products without breaking existing client code.
 *
 * Cons:
 * - The code may become more complicated than it should be, since a lot of new interfaces and classes are introduced along with the pattern.
 */
fun main() {
    val factory = when (furnitureType) {
        FurnitureType.MODERN -> ModernFurnitureFactory
        FurnitureType.CLASSIC -> ClassicFurnitureFactory
    }

    val shop = FurnitureShop(factory)
    shop.orderSofa()
}