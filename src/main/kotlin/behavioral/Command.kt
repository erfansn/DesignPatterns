package behavioral

typealias Command = () -> Unit

class Light {
    fun on() {
        println("Light is on")
    }

    fun off() {
        println("Light is off")
    }
}

val LightOnCommand = fun(light: Light): Command = fun() {
    light.on()
}

val LightOffCommand = fun(light: Light): Command = fun() {
    light.off()
}

class Stereo {
    fun on() {
        println("Stereo is on")
    }

    fun off() {
        println("Stereo is off")
    }

    fun setCD() {
        println("Stereo is set for CD input")
    }

    fun setDVD() {
        println("Stereo is set for DVD input")
    }

    fun setRadio() {
        println("Stereo is set for Radio")
    }

    fun setVolume(volume: Int) {
        println("Stereo volume set to $volume")
    }
}

val StereoOffCommand = fun(stereo: Stereo): Command = fun() {
    stereo.off()
}

val StereoOnWithCDCommand = fun(stereo: Stereo): Command = fun() {
    stereo.on()
    stereo.setCD()
    stereo.setVolume(11)
}

class SimpleRemoteControl {

    private lateinit var command: Command

    fun setCommand(slot: Command) {
        command = slot
    }

    fun onButtonClick() {
        command()
    }
}

/**
 * Pros:
 * - Single Responsibility Principle. You can decouple classes that invoke operations from classes that perform these operations.
 * - Open/Closed Principle. You can introduce new commands into the app without breaking existing client code.
 * - You can implement undo/redo.
 * - You can implement deferred execution of operations.
 * - You can assemble a set of simple commands into a complex one.
 *
 * Cons:
 * - The code may become more complicated since you’re introducing a whole new layer between senders and receivers.
 */
fun main() {
    val remote = SimpleRemoteControl()
    val light = Light()
    val stereo = Stereo()

    remote.setCommand(LightOnCommand(light))
    remote.onButtonClick()

    remote.setCommand(StereoOnWithCDCommand(stereo))
    remote.onButtonClick()

    remote.setCommand(StereoOffCommand(stereo))
    remote.onButtonClick()
}
