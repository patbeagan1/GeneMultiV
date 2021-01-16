import kotlin.math.roundToInt

class Chromosome {
    private var x = 0
    private var y = 0
    private var _fitness = 0
    fun generate(seed: Chromosome?) {
        seed!!
        x = seed.getX() + Math.random().roundToInt()
        y = seed.getY() + Math.random().roundToInt()
    }

    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }

    fun setX(x: Int) {
        this.x = x
        _fitness = 0
    }

    fun setY(y: Int) {
        this.y = y
        _fitness = 0
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