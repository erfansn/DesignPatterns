package structural

import creational.Color

// Intrinsic state
data class TreeType(val name: String, val color: Any, val texture: Any) {

    fun draw(canvas: Any, x: Float, y: Float) = Unit
}

object TreeFactory {

    private val treeTypes = mutableListOf<TreeType>()

    fun getTreeType(name: String, color: Any, texture: Any): TreeType {
        val type = treeTypes.find {
            it == it.copy(name = name, color = color, texture = texture)
        }
        return type ?: TreeType(name, color, texture).also { treeTypes.add(it) }
    }
}

// Extrinsic state
class Tree(
    private val x: Float,
    private val y: Float,
    private val type: TreeType
) {
    fun draw(canvas: Any) {
        type.draw(canvas, x, y)
    }
}

class Forest {

    private val trees = mutableListOf<Tree>()

    fun plantTree(x: Float, y: Float, name: String, color: Any, texture: Any) {
        val type = TreeFactory.getTreeType(name, color, texture)
        val tree = Tree(x, y, type)
        trees.add(tree)
    }

    fun draw(canvas: Any) {
        for (tree in trees) {
            tree.draw(canvas)
        }
    }
}

/**
 * Pros:
 * - You can save lots of RAM, assuming your program has tons of similar objects.
 *
 * Cons:
 * - You might be trading RAM over CPU cycles when some of the context data needs
 * to be recalculated each time somebody calls a flyweight method.
 * - The code becomes much more complicated. New team members will always be wondering
 * why the state of an entity was separated in such a way.
 */
fun main() {
    val forest = Forest()
    forest.plantTree(1.0f, 0.0f, "apple", Color.BLACK, Unit)
    forest.plantTree(1.0f, 0.0f, "peach", Color.WHITE, Unit)
    forest.plantTree(1.0f, 0.0f, "peach", Color.WHITE, Unit)
    forest.draw(Unit)
}
