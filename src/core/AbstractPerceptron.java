package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ls.LearningSchedule;
import reader.DataFrame;
import util.DoubleOps;

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
     * The learning schedule of the perceptron if it is used as an
     * independent model.
     */
    private LearningSchedule ls;

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
        this.W = DoubleOps.random(size);

        // inititialize bias
        this.b = DoubleOps.random(1)[0];
    }

    
    @Override
    public void setLearningRate(Double learningRate) {l = learningRate;}

    
    @Override
    public void setLearningSchedule(LearningSchedule ls) {this.ls = ls;}

    
    @Override
    public Double predict(Double[] inputs) {
        // assigns inputs for storage
        X = inputs;

        // follows the regression y = b + w1*x1 + w2*x2 + ...
        p = DoubleOps.dotP(inputs, W) + b;

        // apply the Perceptron learning rule
        p = learningRule(p);

        return p;
    }


    @Override
    public List<Double> predict(List<Double[]> inputs) {
        List<Double> out = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
           out.add(predict(inputs.get(i))); 
        }
        return out;
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
    public void train(DataFrame<Double> df) {
        List<Double[]> inputs = df.inputs();
        List<Double[]> outputs = df.outputs();
        for (int i = 0; i < df.size(); i++) {
            train(inputs.get(i), outputs.get(i)[0], i);
        }
    }


    @Override
    public int size() {return W.length;}

    
    @Override
    public String toString() {return Arrays.toString(W);}


    @Override
    public Double[] train(Double[] inputs, Double outputs, int iterations) {
        // set learning rate
        l = ls.rate(iterations);

        // make a prediction using the current model
        Double y = predict(inputs);

        // calculate the signal using the outputs
        Double signal = outputs - y;

        // output layer: update the weights and pass on the signals
        return update(signal);
    
    }

}
