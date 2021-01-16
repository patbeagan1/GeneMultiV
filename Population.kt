import kotlin.math.roundToInt

class Population(pop: Int) {
    private var chro: Array<Chromosome?> = arrayOfNulls(pop)
    private var fitchro: Array<Chromosome?>

    private fun size(): Int = chro.size

    fun populate() {
        (0 until size()).forEach { i ->
            chro[i] = Chromosome().apply {
                generate(fitchro[Math.random().roundToInt() * (selectRate - 1)])
            }
        }
    }

    fun selection() {
        (0 until selectRate).forEach { u ->
            fitchro[u] = fittest
            fittest!!.kill()
        }
    }

    fun mutation() {
        chro.forEach { c ->
            if (Math.random() <= mutationRate) {
                c!!.setX(c.getX() + (Math.random() * mutationFactor).roundToInt())
            }
            if (Math.random() <= mutationRate) {
                c!!.setY(c.getY() + (Math.random() * mutationFactor).roundToInt())
            }
            if (Math.random() <= mutationRate) {
                c!!.setX(c.getX() - (Math.random() * mutationFactor).roundToInt())
            }
            if (Math.random() <= mutationRate) {
                c!!.setY(c.getY() - (Math.random() * mutationFactor).roundToInt())
            }
        }
    }

    fun crossover() {
        for (i in 0 until selectRate) {
            for (j in 0 until selectRate) {
                if (Math.random() <= crossRate) {
                    val u = fitchro[i]!!.getX()
                    fitchro[i]!!.setX(fitchro[j]!!.getX())
                    fitchro[j]!!.setX(u)
                }
                if (Math.random() <= crossRate) {
                    val u = fitchro[i]!!.getY()
                    fitchro[i]!!.setY(fitchro[j]!!.getY())
                    fitchro[j]!!.setY(u)
                }
            }
        }
    }

    fun evaluation(): Int {
        return fittest!!.fitness
    }

    val fittest: Chromosome?
        get() {
            var fittest = chro[0]
            for (i in 0 until size()) {
                if (fittest!!.fitness <= chro[i]!!.fitness) {
                    fittest = chro[i]
                }
            }
            return fittest
        }

    companion object {
        private const val crossRate = 0.5
        private const val mutationRate = 0.02
        private const val selectRate = 5
        private const val mutationFactor = 10
    }

    init {
        fitchro = arrayOfNulls(selectRate)
        (0 until size()).forEach { i ->
            chro[i] = Chromosome().apply {
                generate(this)
            }
        }
    }
}