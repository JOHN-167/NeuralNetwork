package src;

import java.util.Random;

/**
 * You must implement both a hard-threshold (perceptron) classifier (AIMA 19.6.4; 3rd ed.
 * 18.6.3) and a logistic classifier (AIMA 19.6.5; 3rd ed. 18.6.4). Almost all of the code can
 * be shared if you design it right.
 * 
 * You should be able to replicate something like the textbook results for the seismic problem 
 * (AIMA Figs. 19.16 and 19.18; 3rd ed. 18.15, 18.16, and 18.18). Note that these
 * report accuracy on the training data, which is not ideal but ok for this project.
 * 
 * Demonstrate your program on the seismic data (both clean and noisy datasets) and the
 * “house votes” dataset (“numerical” version), all of which are provided in our code bundle.
 * 
 * Your program must produce the data used for graphs like those seen in the textbook,
 * and the graphs must must be included in your report.
 */


abstract class Perceptron {
    
    protected double[] inputs;
    protected double output;
    
    protected int size;
    protected double[] W; // weight space
    protected double b; // w0
    
    public double l; // learning rate
    protected Random rd = new Random();

    abstract public double eval(double inputs);

    public Perceptron(int inputSize) {
        W = new double[inputSize];
        for (int i = 0; i < W.length; i++) {
            W[i] = rd.nextDouble();
        }
        b = rd.nextDouble(0.1, 1); // ensure bias != 0
    }

    public double predict(double[] inputs) {
        inputs = inputs.clone();
        double out = 0;
        assert inputs.length == size;
        for (int i = 0; i < size; i++) {
            out =  inputs[i]*W[i];
        }
        out += b;
        output = eval(out);
        return output;
    }

}