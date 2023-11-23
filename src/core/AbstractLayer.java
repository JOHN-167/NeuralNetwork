package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.DoubleOps;

abstract public class AbstractLayer implements Layer {
    

    /**
     * The array of Perceptron that forms the layer
     */
    protected Perceptron[] units;

    
    /** 
     * The number of inputs received by the neural network,
     * i.e. the size of each Perceptron unit
     */
    protected int inputSize;


    /**
     * A constructor that specifies the size of the single layer of
     * the neural network
     * @param size
     */
    public AbstractLayer(int size, int inputSize) {
        units = new Perceptron[size];
        initializeWeights(inputSize);
    }

    
    @Override
    public Double[] predict(Double[] inputs) {
        Double[] outputs = new Double[size()];
        for (int i = 0; i < size(); i++) {outputs[i] = units[i].predict(inputs);}
        return outputs;
    }


    @Override
    public List<Double[]> predict(List<Double[]> inputs) {
        List<Double[]> out = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
           out.add(predict(inputs.get(i))); 
        }
        return out;
    }

    
    @Override
    public Double[] update(Double[] signals) {
        // update the weights and pass on the signals
        Double[] S = new Double[inputSize];
        for (int i = 0; i < inputSize; i++) {S[i] = 0.0;}
        for (int i = 0; i < size(); i++) {S = DoubleOps.sum(S, units[i].update(signals[i]));}

        return S;
    }

    
    @Override
    public void setLearningRate(Double learningRate) {
        for (int i = 0; i < size(); i++) {units[i].setLearningRate(learningRate);}
    }

    
    @Override
    public int size() {return units.length;}
    
    
    @Override
    public String toString() {return Arrays.toString(units);}

}
