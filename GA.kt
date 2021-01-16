object GA {
    @JvmStatic
    fun main(args: Array<String>) {
        val pop = Population(50)
        var gen = 0
        var fit = 0
        pop.initialize()
        while (fit < 10000000) {
            gen++
            pop.selection(pop)
            pop.crossover()
            pop.mutation()
            pop.populate()
            fit = pop.evaluation()
            println("Gen: $gen Fittest: $fit")
        }
        println("Found solution!")
        println("Generation: $gen")
        println("Genes:")
        println("Invest " + pop.fittest?.getX() + " in company X.")
        println("Invest " + pop.fittest?.getY() + " in company Y.")
    }
}