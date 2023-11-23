package bn.src.base;

import bn.src.core.AbstractPerceptron;

/**
 * A LinearClassifier is a Perceptron that performs a linear regression
 * and apply a hard threshold to the output
 */
public class LinearClassifier extends AbstractPerceptron {
    
    /**The hard threshold to the output*/
    private Double threshold;

    /**
     * A constructor that specifies the hard threshold cutoff
     * @param size the size of the Unit
     * @param threshold the hard threshold
     */
    public LinearClassifier(int size, Double threshold) {
        super(size);
        this.threshold = threshold;
    }
    
    /**
     * A constructor that uses the default hard threshold cutoff of 0
     * @param threshold the hard threshold
     */
    public LinearClassifier(int size) {this(size, 0.0);}

    @Override
    public Double learningRule(Double p) {return (p > threshold)? 1.0 : 0.0;}

    @Override
    public Double reverseLearningRule(Double p) {return 1.0;}

    /**
     * Set threshold to a new level
     * @param threshold new level
     */
    public void setThreshold(Double threshold) {this.threshold = threshold;}


}
