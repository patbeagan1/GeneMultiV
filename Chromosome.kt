import kotlin.math.roundToInt

class Chromosome {
    var fitness = 0
    var x = 0
        set(x) {
            field = x
            fitness = 0
        }
    var y = 0
        set(y) {
            field = y
            fitness = 0
        }

    fun generateFrom(seed: Chromosome) {
        x = seed.x + Math.random().roundToInt()
        y = seed.y + Math.random().roundToInt()
    }

    fun kill() {
        fitness = -1
    }
}