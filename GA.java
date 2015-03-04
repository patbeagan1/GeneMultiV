

public class GA {
	
    public static void main(String[] args) {

    	Population pop = new Population(50);
    	int gen = 0;
    	int fit = 0;
    	pop.initialize();
    	
        while (fit < 10000000) {
        	gen++;
            pop.selection(pop);
            pop.crossover();
            pop.mutation();
            pop.populate();
            fit = pop.evaluation();
            
            System.out.println("Gen: " + gen + " Fittest: " + fit);
        }
        System.out.println("Found solution!");
        System.out.println("Generation: " + gen);
        System.out.println("Genes:");
        System.out.println("Invest "+pop.getFittest().getx()+ " in company X.");
        System.out.println("Invest "+pop.getFittest().gety()+ " in company Y.");
    }
}
