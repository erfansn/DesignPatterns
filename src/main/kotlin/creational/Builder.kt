package creational

enum class Color { BLACK, WHITE }
enum class Model { BMW, FORD }
enum class FuelType { GASOLINE, ELECTRICITY }

data class Car(
    val model: Model,
    val color: Color = Color.BLACK,
    val automatic: Boolean = false,
    val openRoof: Boolean = false,
    val fuelType: FuelType = FuelType.GASOLINE
)

/** Due to the features of `default and named arguments`, this pattern is not used much in Kotlin */
fun main() {
    Car(
        model = Model.BMW,
        color = Color.WHITE,
        automatic = true,
    )

    Car(
        model = Model.FORD,
        fuelType = FuelType.ELECTRICITY,
        openRoof = true,
    )
}
