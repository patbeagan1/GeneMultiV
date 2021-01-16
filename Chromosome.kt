import kotlin.math.roundToInt

class Chromosome {
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
    private var _fitness = 0
    fun generate(seed: Chromosome) {
        x = seed.x + Math.random().roundToInt()
        y = seed.y + Math.random().roundToInt()
    }

    val fitness: Int
        get() {
            if (_fitness == 0) {
                _fitness =  //3*x+4*y;
                        -((x - 3750) * (x - 3750)) + 570 * x + -2 * ((y - 5000) * (y - 5000)) + 1500 * y + 25
            }
            return _fitness
        }

    fun kill() {
        _fitness = -1
    }
}