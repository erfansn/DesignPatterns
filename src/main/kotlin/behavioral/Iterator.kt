package behavioral

import structural.*
import structural.Button

class ViewGroupIterator(viewGroup: ViewGroup) : Iterator<View> {

    private var currentViewIndex = 0

    private val viewGroupStructureAsList = viewGroup.views.flatMap {
        if (it is ViewGroup) it.views else listOf(it)
    }

    override fun hasNext(): Boolean {
        return currentViewIndex < viewGroupStructureAsList.size
    }

    override fun next(): View {
        return viewGroupStructureAsList[currentViewIndex++]
    }
}

/**
 * Pros:
 * - Single Responsibility Principle. You can clean up the client code and the collections by extracting bulky traversal
 * algorithms into separate classes.
 * - Open/Closed Principle. You can implement new types of collections and iterators and pass them to existing code without breaking anything.
 * - You can iterate over the same collection in parallel because each iterator object contains its own iteration state.
 * - For the same reason, you can delay an iteration and continue it when needed.
 *
 * Cons:
 * - Applying the pattern can be an overkill if your app only works with simple collections.
 * - Using an iterator may be less efficient than going through elements of some specialized collections directly.
 */
fun main() {
    val button = Button(place = Coordinate(10, 40))
    val imageButton = ImageButton(imagePath = "fakePath")
    val childViewGroup = ViewGroup(button, imageButton)
    val viewGroup = ViewGroup(button, imageButton, childViewGroup)

    val viewGroupIterator = ViewGroupIterator(viewGroup = viewGroup)
    for (view in viewGroupIterator) {
        println(view)
    }
}
