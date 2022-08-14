package behavioral

interface ComponentWithContextualHelp {
    fun showHelp()
}

abstract class Component : ComponentWithContextualHelp {

    var tooltipText: String? = null

    lateinit var container: Container

    override fun showHelp() {
        tooltipText?.let {
            println(it)
        } ?: container.showHelp()
    }
}

abstract class Container : Component() {

    private val children: MutableList<Component> = mutableListOf()

    fun add(child: Component) {
        children.add(child)
        child.container = this
    }
}

class Button : Component()

class Panel : Container() {

    var modalHelpText: String? = null

    override fun showHelp() {
        modalHelpText?.let {
            // Show a modal window with the help text.
            println(it)
        } ?: super.showHelp()
    }
}

class Dialog : Container() {

    var wikiPageURL: String? = null

    override fun showHelp() {
        wikiPageURL?.let {
            // Open the wiki help page.
            println(it)
        } ?: super.showHelp()
    }
}

/**
 * Pros:
 * - You can control the order of request handling.
 * - Single Responsibility Principle. You can decouple classes that invoke operations from classes that perform operations.
 * - Open/Closed Principle. You can introduce new handlers into the app without breaking the existing client code.
 *
 * Cons:
 * - Some requests may end up unhandled.
 */
fun main() {
    val dialog = Dialog()
    dialog.wikiPageURL = "http://..."

    val panel = Panel()
    panel.modalHelpText = null

    val ok = Button()
    ok.tooltipText = null

    val cancel = Button()
    cancel.tooltipText = "Dismiss dialog"

    panel.add(ok)
    panel.add(cancel)
    dialog.add(panel)

    // After press F1 key
    ok.showHelp()
}
