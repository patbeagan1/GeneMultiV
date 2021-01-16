import kotlin.math.roundToInt

class Population(pop: Int) {
    private var population = Array(pop) {
        Chromosome().apply {
            generate(this)
        }
    }.toList()
    private var survivors = Array(selectRate) { Chromosome() }

    fun populate() {
        population = population.map {
            Chromosome().apply {
                generate(survivors[Math.random().roundToInt() * (selectRate - 1)])
            }
        }
    }

    fun selection() {
        (0 until selectRate).forEach { u ->
            survivors[u] = fittest
            fittest.kill()
        }
    }

    fun mutation() {
        population.forEach { c ->
            if (Math.random() <= mutationRate) {
                c.x = c.x + (Math.random() * mutationFactor).roundToInt()
            }
            if (Math.random() <= mutationRate) {
                c.y = c.y + (Math.random() * mutationFactor).roundToInt()
            }
            if (Math.random() <= mutationRate) {
                c.x = c.x - (Math.random() * mutationFactor).roundToInt()
            }
            if (Math.random() <= mutationRate) {
                c.y = c.y - (Math.random() * mutationFactor).roundToInt()
            }
        }
    }

    fun crossover() {
        for (i in 0 until selectRate) {
            for (j in 0 until selectRate) {
                if (Math.random() <= crossRate) {
                    val u = survivors[i].x
                    survivors[i].x = survivors[j].x
                    survivors[j].x = u
                }
                if (Math.random() <= crossRate) {
                    val u = survivors[i].y
                    survivors[i].y = survivors[j].y
                    survivors[j].y = u
                }
            }
        }
    }

    fun evaluation(): Int = fittest.fitness

    val fittest: Chromosome
        get() = population.maxByOrNull { it.fitness }!!

    companion object {
        private const val crossRate = 0.5
        private const val mutationRate = 0.02
        private const val selectRate = 5
        private const val mutationFactor = 10
    }
}