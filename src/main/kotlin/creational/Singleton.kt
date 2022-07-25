package creational

object Score {

    private val numbers = mutableListOf<Int>()

    fun add(number: Int) = also {
        numbers += number
    }

    fun average() = numbers.average()
}

class App {

    companion object {

        @Volatile private var db: Any? = null

        fun getDatabase(): Any {
            return db ?: synchronized(this) {
                Any().also {
                    db = it
                }
            }
        }
    }
}
