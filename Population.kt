class Population(pop: Int) {
    var chro: Array<Chromosome?> = arrayOfNulls(pop)
    var fitchro: Array<Chromosome?>
    operator fun get(num: Int): Chromosome? {
        return chro[num]
    }

    operator fun set(num: Int, c: Chromosome?) {
        chro[num] = c
    }

    private fun size(): Int = chro.size

    fun initialize() {
        for (i in 0 until size()) {
            val c = Chromosome()
            c.generate(c)
            set(i, c)
        }
    }

    fun populate() {
        for (i in 0 until size()) {
            val c = Chromosome()
            c.generate(fitchro[Math.round(Math.random()).toInt() * (selectRate - 1)])
            set(i, c)
        }
    }

    fun selection(pop: Population?) {
        for (u in 0 until selectRate) {
            fitchro[u] = fittest
            fittest!!.kill()
        }
    }

    fun mutation() {
        for (c in chro) {
            if (Math.random() <= mutationRate) {
                c!!.setX(c.getX() + Math.round(Math.random() * mutationFactor).toInt())
            }
            if (Math.random() <= mutationRate) {
                c!!.setY(c.getY() + Math.round(Math.random() * mutationFactor).toInt())
            }
            if (Math.random() <= mutationRate) {
                c!!.setX(c.getX() - Math.round(Math.random() * mutationFactor).toInt())
            }
            if (Math.random() <= mutationRate) {
                c!!.setY(c.getY() - Math.round(Math.random() * mutationFactor).toInt())
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
                if (fittest!!.fitness <= get(i)!!.fitness) {
                    fittest = get(i)
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
    }
}