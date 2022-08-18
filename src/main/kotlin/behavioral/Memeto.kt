package behavioral

typealias Cursor = Pair<Int, Int>

class Editor {

    var text: String = ""
    var cursor: Cursor = 0 to 0
    var width: Int = 0

    fun createSnapshot() = Snapshot(this, text, cursor, width)
}

class Snapshot(
    private val editor: Editor,
    private val text: String,
    private val cursor: Cursor,
    private val width: Int
) {
    fun restore() {
        editor.text = text
        editor.cursor = cursor
        editor.width = width
    }
}

class CommandCaretaker(private val editor: Editor) {

    private lateinit var snapshot: Snapshot

    fun makeBackup() {
        snapshot = editor.createSnapshot()
    }

    fun undo() {
        if (::snapshot.isInitialized) {
            snapshot.restore()
        }
    }
}

/**
 * Pros:
 * - You can produce snapshots of the object’s state without violating its encapsulation.
 * - You can simplify the originator’s code by letting the caretaker maintain the history of the originator’s state.
 *
 * Cons:
 * - The app might consume lots of RAM if clients create mementos too often.
 * - Caretakers should track the originator’s lifecycle to be able to destroy obsolete mementos.
 * - Most dynamic programming languages, such as PHP, Python and JavaScript,
 * can’t guarantee that the state within the memento stays untouched.
 */
fun main() {
    val editor = Editor()
    val commandCaretaker = CommandCaretaker(editor)

    editor.text = "Hello World"
    commandCaretaker.makeBackup()

    editor.text = ""
    commandCaretaker.undo()

    print(editor.text)
}