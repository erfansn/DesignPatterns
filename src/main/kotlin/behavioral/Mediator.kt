package behavioral

interface ProductManager {
    fun isAllGood(majorRelease: Boolean): Boolean
}

interface Canary

object Michael : Canary, ProductManager {
    private val kenny = Kenny(this)

    private val brad = Brad(this)
    override fun isAllGood(majorRelease: Boolean): Boolean {
        if (!kenny.isEating() && !kenny.isSleeping()) {
            println(kenny.doesMyCodeWork())
        } else if (!brad.isEating() && !brad.isSleeping()) {
            println(brad.doesMyCodeWork())
        }
        return true
    }
}

interface QA {
    fun doesMyCodeWork(): Boolean
}

interface Parrot {
    fun isEating(): Boolean
    fun isSleeping(): Boolean
}

class Kenny(private val productManager: ProductManager) : QA, Parrot {
    override fun isSleeping(): Boolean {
        return false
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun doesMyCodeWork(): Boolean {
        return true
    }
}

class Brad(private val productManager: ProductManager) : QA, Parrot {
    override fun isSleeping(): Boolean {
        return false
    }

    override fun isEating(): Boolean {
        return false
    }

    override fun doesMyCodeWork(): Boolean {
        return true
    }
}

class MyCompany(private val productManager: ProductManager) {
    fun taskCompleted(isMajorRelease: Boolean) {
        println(productManager.isAllGood(isMajorRelease))
    }
}

/**
 * Pros:
 * - Single Responsibility Principle. You can extract the communications between various components into a single place,
 * making it easier to comprehend and maintain.
 * - Open/Closed Principle. You can introduce new mediators without having to change the actual components.
 * - You can reduce coupling between various components of a program.
 * - You can reuse individual components more easily.
 *
 * Cons:
 * - Over time a mediator can evolve into a God Object.
 */
fun main() {
    val myCompany = MyCompany(Michael)
    myCompany.taskCompleted(true)
}
