package behavioral

import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.random.Random

typealias Listener = (FloatArray) -> Unit

interface Sensor {
    val value: FloatArray
}

object SensorManager {

    private var listeners = mutableMapOf<Sensor, Listener>()

    fun register(sensor: Sensor, listener: Listener) {
        listeners[sensor] = listener
    }

    fun unregister(sensor: Sensor) {
        listeners.remove(sensor)
    }

    fun notifyObservers(sensor: Sensor, value: FloatArray) {
        for (listener in listeners.filterKeys { it == sensor }.values) {
            listener(value)
        }
    }
}

object TemperatureSensor : Sensor {

    override var value: FloatArray = floatArrayOf(0f)

    init {
        thread {
            while (true) {
                value[0] = Random.nextFloat() * 100f
                SensorManager.notifyObservers(this, value)
                sleep(1000)
            }
        }
    }
}

object AccelerationSensor : Sensor {

    override val value: FloatArray = floatArrayOf(0f)

    init {
        thread {
            while (true) {
                value[0] = Random.nextInt(100, 200).toFloat()
                SensorManager.notifyObservers(this, value)
                sleep(500)
            }
        }
    }
}

/**
 * Pros:
 * Open/Closed Principle. You can introduce new subscriber classes without having to change the publisher’s code
 * (and vice versa if there’s a publisher interface).
 * You can establish relations between objects at runtime.
 *
 * Cons:
 * Subscribers are notified in random order.
 */
fun main() {
    val sensorManager = SensorManager
    val temperatureSensor = TemperatureSensor
    val accelerationSensor = AccelerationSensor

    sensorManager.register(temperatureSensor) {
        println("Update temperature ${it.first()}")
    }
    sensorManager.register(accelerationSensor) {
        println("Update acceleration ${it.first()}")
    }
}