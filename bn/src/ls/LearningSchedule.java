package bn.src.ls;

public interface LearningSchedule {
    
    /**
     * Return the learning rate based on the number of iterations
     */
    public double rate(int iterations);

}
