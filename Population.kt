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
        get() = population.maxByOrNull { it.fitness }!!

    companion object {
        private const val crossRate = 0.5
        private const val mutationRate = 0.02
        private const val selectRate = 5
        private const val mutationFactor = 10
    }
}