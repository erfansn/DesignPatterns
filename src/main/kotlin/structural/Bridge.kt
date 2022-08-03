package structural

interface Device {
    val isEnabled: Boolean
    fun enable()
    fun disable()
    var volume: Int
    var channel: Int
}

class TV : Device {
    override val isEnabled: Boolean
        get() = TODO("Not yet implemented")

    override fun enable() {
        TODO("Not yet implemented")
    }

    override fun disable() {
        TODO("Not yet implemented")
    }

    override var volume: Int
        get() = TODO("Not yet implemented")
        set(value) {}

    override var channel: Int
        get() = TODO("Not yet implemented")
        set(value) {}
}

class Radio : Device {

    override val isEnabled: Boolean
        get() = TODO("Not yet implemented")

    override fun enable() {
        TODO("Not yet implemented")
    }

    override fun disable() {
        TODO("Not yet implemented")
    }

    override var volume: Int
        get() = TODO("Not yet implemented")
        set(value) {}

    override var channel: Int
        get() = TODO("Not yet implemented")
        set(value) {}
}

open class RemoteController(protected open val device: Device) {
    fun togglePower() {
        if (device.isEnabled) device.disable() else device.enable()
    }

    fun volumeDown() {
        device.volume -= 10
    }

    fun volumeUp() {
        device.volume += 10
    }

    fun channelDown() {
        device.channel -= 1
    }

    fun channelUp() {
        device.channel += 1
    }
}

class AdvancedRemoteController(override val device: Device) : RemoteController(device) {

    fun mute() {
        device.volume = 0
    }
}

/**
 * Pros:
 * - You can create platform-independent classes and apps.
 * - The client code works with high-level abstractions. It isn’t exposed to the platform details.
 * - Open/Closed Principle. You can introduce new abstractions and implementations independently from each other.
 * - Single Responsibility Principle. You can focus on high-level logic in the abstraction and on platform details in the implementation.
 *
 * Cons:
 * - You might make the code more complicated by applying the pattern to a highly cohesive class.
 */
fun main() {
    val tv = TV()
    val remoteController = RemoteController(tv)
    remoteController.togglePower()

    val radio = Radio()
    val advancedRemoteController = AdvancedRemoteController(radio)
    advancedRemoteController.mute()
}
