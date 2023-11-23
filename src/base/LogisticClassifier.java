package base;

import core.AbstractPerceptron;
import util.VectorOps;

public class LogisticClassifier extends AbstractPerceptron {

    /**
     * A constructor that specifies the size of the inputs
     * received by the Unit
     * @param size the size of the inputs
     */
    public LogisticClassifier(int size) {super(size);}

    @Override
    public Double learningRule(Double p) {return VectorOps.sigmoid(p);}

    @Override
    public Double reverseLearningRule(Double p) {return p*(1-p);}
    
}
