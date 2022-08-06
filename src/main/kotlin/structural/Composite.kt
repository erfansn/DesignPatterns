package structural

import kotlin.io.path.Path

typealias Coordinate = Pair<Int, Int>

interface View {
    fun move(coordinate: Coordinate)
    fun draw()
}

open class Button(private var place: Coordinate = Coordinate(0, 0)) : View {

    override fun move(coordinate: Coordinate) {
        place = place.copy(
            place.first + coordinate.first,
            place.second + coordinate.second
        )
    }

    override fun draw() {
        TODO("Not yet implemented")
    }
}

class ImageButton(
    center: Coordinate = Coordinate(0, 0),
    private val imagePath: String
) : Button(center) {

    override fun draw() {
        super.draw()
        val path = Path(imagePath)
    }
}

class ViewGroup(private val views: List<View>) : View {

    constructor(vararg graphics: View) : this(graphics.toList())

    override fun move(coordinate: Coordinate) {
        views.forEach {
            it.move(coordinate)
        }
    }

    override fun draw() {
        views.forEach(View::draw)
    }
}

/**
 * Pros:
 * - You can work with complex tree structures more conveniently: use polymorphism and recursion to your advantage.
 * - Open/Closed Principle. You can introduce new element types into the app without breaking the existing code,
 * which now works with the object tree.
 *
 * Cons:
 * - It might be difficult to provide a common interface for classes whose functionality differs too much.
 * In certain scenarios, you’d need to overgeneralize the component interface, making it harder to comprehend.
 */
fun main() {
    val button = Button(place = Coordinate(10, 40))
    val imageButton = ImageButton(imagePath = "fakePath")
    val childViewGroup = ViewGroup(button, imageButton)
    val viewGroup = ViewGroup(button, imageButton, childViewGroup)

    viewGroup.move(Coordinate(10, 30))
}


