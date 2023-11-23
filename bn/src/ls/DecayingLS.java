package bn.src.ls;

public class DecayingLS implements LearningSchedule {

    /**
     * The seed rate that is used for all rate-related calculations
     */
    private double seed;

    public DecayingLS(double seed) {this.seed = seed;}
    
    @Override
    public double rate(int iterations) {return seed/(1+iterations);}
    
}
