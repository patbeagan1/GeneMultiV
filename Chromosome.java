
public class Chromosome {
	private int x=0,y=0;
    private int fitness = 0;

    public void generate(Chromosome seed) {
            x = seed.getx() + (int) Math.round(Math.random());
            y = seed.gety() + (int) Math.round(Math.random());
    }
    
    public int getx(){return x;}
    public int gety(){return y;}
    public void setx(int x){
    	this.x=x;
    	fitness=0;
    }
    public void sety(int y){
    	this.y=y;
    	fitness=0;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = //3*x+4*y;
            		-((x-3750)*(x-3750))+ 
            		570*x+ 
            		-2*((y-5000)*(y-5000))+
            		1500*y
            		+ 25;
        }
        return fitness;
    }
    public void kill(){
    	fitness=-1;
    }
}
