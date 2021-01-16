object GA {
    @JvmStatic
    fun main(args: Array<String>) {
        val pop = Population(50)
        var gen = 0
        var fit = 0
        while (fit < 10000000) {
            gen++
            pop.selection()
            pop.crossover()
            pop.mutation()
            pop.populate()
            fit = pop.evaluation()
            println("Gen: $gen Fittest: $fit")
        }
        println("Found solution!")
        println("Generation: $gen")
        println("Genes:")
        println("Invest " + pop.fittest?.x + " in company X.")
        println("Invest " + pop.fittest?.y + " in company Y.")
    }
}