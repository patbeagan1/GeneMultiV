

public class Population {

    Chromosome[] chro;
    Chromosome[] fitchro;
    private static final double crossRate = 0.5;
    private static final double mutationRate = 0.02;
    private static final int selectRate = 5;
    private static final int mutationFactor = 10;

    public Population(int pop) {
        chro = new Chromosome[pop];
        fitchro = new Chromosome[selectRate];
    }

    public Chromosome get(int num) {return chro[num];}
    public void set(int num, Chromosome c){chro[num]=c;}
    public int size() {return chro.length;}

    public void initialize(){
    	for (int i = 0; i < size(); i++) {
        	Chromosome c = new Chromosome();
                c.generate(c);
                set(i, c);
        }
    }
    public void populate(){
    	for (int i = 0; i < size(); i++) {
    		Chromosome c= new Chromosome();
    		c.generate(fitchro[(int) Math.round(Math.random())*(selectRate-1)]);
            set(i, c);
        }
    }
    public void selection(Population pop){
       
        for(int u=0;u<selectRate;u++){
        	fitchro[u]=getFittest();
        	getFittest().kill();
        }
    }
    public void mutation() {
    	for(Chromosome c: chro){
	    	if (Math.random() <= mutationRate) {
	    		c.setx(c.getx() + (int) Math.round(Math.random()*mutationFactor));
	        }
	    	if (Math.random() <= mutationRate) {
	    		c.sety(c.gety() + (int) Math.round(Math.random()*mutationFactor));
	        }
	    	if (Math.random() <= mutationRate) {
	    		c.setx(c.getx() - (int) Math.round(Math.random()*mutationFactor));
	        }
	    	if (Math.random() <= mutationRate) {
	    		c.sety(c.gety() - (int) Math.round(Math.random()*mutationFactor));
	        }
    	}
    }
    public void crossover() {
        for (int i = 0; i < selectRate; i++) {
        	for (int j = 0; j < selectRate; j++) {
		        if (Math.random() <= crossRate) {
		        	int u = fitchro[i].getx();
		        	fitchro[i].setx(fitchro[j].getx());
		        	fitchro[j].setx(u);	
		        }
		        if (Math.random() <= crossRate) {
		        	int u = fitchro[i].gety();
		        	fitchro[i].sety(fitchro[j].gety());
		        	fitchro[j].sety(u);	
		        }
        	}
        }
    }
    public int evaluation(){
    	return getFittest().getFitness();
    }
    public Chromosome getFittest() {
        Chromosome fittest = chro[0];
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= get(i).getFitness()) {
                fittest = get(i);
            }
        }
        return fittest;
    }
}
