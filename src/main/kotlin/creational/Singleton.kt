package creational

interface AppDao {
    fun insert(value: String)
    fun update(index: Int, value: String)
    fun delete(index: Int)
    fun fetch(): List<String>
}

class AppDaoImpl : AppDao {

    private val list = mutableListOf<String>()

    override fun insert(value: String) {
        list += value
    }

    override fun update(index: Int, value: String) {
        list[index] = value
    }

    override fun delete(index: Int) {
        list.removeAt(index)
    }

    override fun fetch() = list
}

interface Database

class OrmDatabase : Database {
    val appDao = AppDaoImpl()
}

/** Create a singleton object with `object` modifier is thread-safe and its instantiation has lazy */
object Application {

    @Volatile
    private var db: OrmDatabase? = null

    fun getDatabase(): OrmDatabase {
        return db ?: synchronized(this) {
            OrmDatabase().also { db = it }
        }
    }

    init {
        println("App launched")
    }
}

/**
 * Pros:
 * - You can be sure that a class has only a single instance.
 * - You gain a global access point to that instance.
 * - The singleton object is initialized only when it’s requested for the first time.
 *
 * Cons:
 * - Violates the Single Responsibility Principle. The pattern solves two problems at the time.
 * - The Singleton pattern can mask bad design, for instance, when the components of the program know too much about each other.
 * - The pattern requires special treatment in a multithreaded environment so that multiple threads won’t create a singleton object several times.
 * - It may be difficult to unit test the client code of the Singleton because many test frameworks rely on inheritance when producing mock objects.
 * - Since the constructor of the singleton class is private and overriding static methods is impossible in most languages,
 * you will need to think of a creative way to mock the singleton. Or just don’t write the tests. Or don’t use the Singleton pattern.
 */
fun main() {
    val db = Application.getDatabase()
    db.appDao.insert("singleton")

    val db2 = Application.getDatabase()
    println(db.appDao.fetch())

    println(Application == Application)
    println(db === db2)
}
