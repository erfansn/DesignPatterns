package structural

interface DataSource {
    fun writeData(byteArray: ByteArray)
    fun readDate(): ByteArray
}

class FileDataSource(private val fileName: String) : DataSource {

    override fun writeData(byteArray: ByteArray) {
        println("Raw write")
    }

    override fun readDate(): ByteArray {
        return byteArrayOf().also { println("Raw read") }
    }
}

class EncryptionDataSource(private val dataSource: DataSource) : DataSource by dataSource {

    override fun writeData(byteArray: ByteArray) {
        println("Encrypted write")
        dataSource.writeData(byteArray)
    }

    override fun readDate(): ByteArray {
        return dataSource.readDate().also { println("Unencrypted read") }
    }
}

class CompressionDataSource(private val dataSource: DataSource) : DataSource by dataSource {

    override fun writeData(byteArray: ByteArray) {
        println("Compressed write")
        dataSource.writeData(byteArray)
    }

    override fun readDate(): ByteArray {
        return dataSource.readDate().also { println("Decompressed read") }
    }
}

/**
 * Pros:
 * - You can extend an object’s behavior without making a new subclass.
 * - You can add or remove responsibilities from an object at runtime.
 * - You can combine several behaviors by wrapping an object into multiple decorators.
 * - Single Responsibility Principle. You can divide a monolithic class that implements many possible variants of
 * behavior into several smaller classes.
 *
 * Cons:
 * - It’s hard to remove a specific wrapper from the wrappers stack.
 * - It’s hard to implement a decorator in such a way that its behavior doesn’t depend on the order in the decorators stack.
 * - The initial configuration code of layers might look pretty ugly.
 */
fun main() {
    var source: DataSource = FileDataSource("fileName")
    source = EncryptionDataSource(source)
    source = CompressionDataSource(source)

    source.writeData(byteArrayOf())
    source.readDate()
}
