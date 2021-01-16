import kotlin.math.roundToInt

class Chromosome {
    private var _fitness = 0
    var x = 0
        set(x) {
            field = x
            _fitness = 0
        }
    var y = 0
        set(y) {
            field = y
            _fitness = 0
        }

    fun generateFrom(seed: Chromosome) {
        x = seed.x + Math.random().roundToInt()
        y = seed.y + Math.random().roundToInt()
    }

    val fitness: Int
        get() {
            if (_fitness == 0) {
                val i0 = x - 3750
                val i1 = y - 5000
                val i2 = 570 * x
                val i3 = 1500 * y + 25
                _fitness = -(i0 * i0) + i2 + -2 * (i1 * i1) + i3
            }
            return _fitness
        }

    fun kill() {
        _fitness = -1
    }
}