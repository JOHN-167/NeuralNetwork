package ls;

public class ConstantLS implements LearningSchedule {

    /**
     * The seed rate that is used for all rate-related calculations
     */
    private double seed;

    public ConstantLS(double seed) {this.seed = seed;}
    
    @Override
    public double rate(int iterations) {return seed;}

}
