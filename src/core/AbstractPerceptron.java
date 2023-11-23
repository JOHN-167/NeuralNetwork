package core;

import java.util.Arrays;

import util.VectorOps;

abstract public class AbstractPerceptron implements Perceptron {


    /**
     * The inputs that are received by the Unit. If the Unit has not
     * received any inputs, then it has not been initialized
     */
    protected Double[] X;


    /**
     * The weight space contains all the weights to adjust the inputs
     * being received by the unit
     */
    protected Double[] W;


    /**
     * The bias is added to the summation of the weighted inputs to
     * create the outputs
     */
    protected Double b;


    /**
     * The learning rate is used to determine the speed of the weight
     * updating process
     */
    private Double l;


    /**
     * The output predicted by the Perceptron. If the function predict
     * has not been called, the variable may not have been initialized. 
     */
    private Double p;

    
    /**
     * A constructor that specifies the size of the inputs
     * received by the Unit
     * @param size the size of the inputs
     */
    public AbstractPerceptron(int size) {
        // initialize weights
        this.W = VectorOps.random(size);

        // inititialize bias
        this.b = VectorOps.random(1)[0];
    }

    
    @Override
    public void setLearningRate(Double learningRate) {l = learningRate;}

    
    @Override
    public Double predict(Double[] inputs) {
        // assigns inputs for storage
        X = inputs;

        // follows the regression y = b + w1*x1 + w2*x2 + ...
        p = VectorOps.dotP(inputs, W) + b;

        // apply the Perceptron learning rule
        p = learningRule(p);

        return p;
    }


    @Override
    public Double[] update(Double signal) {
        Double[] S = new Double[W.length];
        
        // calculate the slope for gradient descent
        Double slope = reverseLearningRule(p);

        // pass on the signals to the connected Unit
        for (int i = 0; i < W.length; i++) {S[i] = signal*slope*W[i];}

        // update the weights
        for (int i = 0; i < W.length; i++) {W[i] += l*slope*signal*X[i];}

        return S;
    }


    @Override
    public int size() {return W.length;}

    
    @Override
    public String toString() {return Arrays.toString(W);}

    
}
