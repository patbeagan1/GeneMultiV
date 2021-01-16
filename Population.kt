import kotlin.math.roundToInt

class Population(pop: Int) {
    private var population = (0 until pop).map {
        Chromosome().apply { generateFrom(this) }
    }
    private var survivors = Array(selectRate) { Chromosome() }

    fun populate() {
        population.forEach { it.generateFrom(survivors.random()) }
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
                c.x += getMutationAmount()
            }
            if (Math.random() <= mutationRate) {
                c.y += getMutationAmount()
            }
            if (Math.random() <= mutationRate) {
                c.x -= getMutationAmount()
            }
            if (Math.random() <= mutationRate) {
                c.y -= getMutationAmount()
            }
        }
    }

    private fun getMutationAmount() = ((Math.random() * mutationFactor)).roundToInt()

    fun crossover() {
        for (i in 0 until selectRate) {
            for (j in 0 until selectRate) {
                if (Math.random() <= crossRate) {
                    exchangeX(survivors[i], survivors[j])
                }
                if (Math.random() <= crossRate) {
                    exchangeY(survivors[i], survivors[j])
                }
            }
        }
    }

    private fun exchangeY(first: Chromosome, second: Chromosome) {
        val u = first.y
        first.y = second.y
        second.y = u
    }

    private fun exchangeX(first: Chromosome, second: Chromosome) {
        val u = first.x
        first.x = second.x
        second.x = u
    }

    fun evaluation(): Int = fittest.fitness

    val fittest: Chromosome
        get() {
            return population.onEach {
                if (it.fitness == 0) {
                    val i0 = it.x - 3750
                    val i1 = it.y - 5000
                    val i2 = 570 * it.x
                    val i3 = 1500 * it.y + 25
                    it.fitness = -(i0 * i0) + i2 + -2 * (i1 * i1) + i3
                }
            }.maxByOrNull { it.fitness }!!
        }

    companion object {
        private const val crossRate = 0.5
        private const val mutationRate = 0.02
        private const val selectRate = 5
        private const val mutationFactor = 10
    }
}