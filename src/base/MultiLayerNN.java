package src.base;

import java.util.ArrayList;
import java.util.List;

import src.core.AbstractNN;
import src.core.Layer;
import src.io.DataFrame;
import src.ls.LearningSchedule;
import src.util.DoubleOps;

/**
 * A multi-layer neural network with a logistic regression for the 
 * hidden layers and a linear regression with a hard threshold for
 * the output layer
 */
public class MultiLayerNN extends AbstractNN {


    /**
     * The array of hidden layers that forms the backbone of the 
     * multi-layered neural network
     */
    private Layer[] hidden;


    /**
     * A constructor that specifies the number of hidden layers, the size of
     * the inputs, the size of hidden layers, and the outputs. It also specifies 
     * for the neural network the cutoff threshold
     * @param nLayers the number of hidden layers
     * @param inputSize the size of the inputs
     * @param hiddenSize the sizes of the hidden layers
     * @param outputSize the size of the output
     * @param threshold the cutoff threshold
     */
    public MultiLayerNN(int inputSize, int[] hiddenSize, int outputSize, Double threshold, LearningSchedule l) {
        this(inputSize, initializeHidden(inputSize, hiddenSize), outputSize, threshold, l);
    }


    /**
     * A constructor that specifies the number of hidden layers, the size of
     * the inputs, the hidden layers, and the outputs. It also specifies for
     * the+ neural network the cutoff threshold
     * @param nLayers the number of hidden layers
     * @param inputSize the size of the inputs
     * @param hidden the array of the hidden layers
     * @param outputSize the size of the output
     * @param threshold the cutoff threshold
     */
    public MultiLayerNN(int inputSize, Layer[] hidden, int outputSize, Double threshold, LearningSchedule l) {
        this.hidden = hidden;
        output = new LinearLayer(outputSize, hidden[hidden.length-1].size(), threshold);
        this.l = l;
    }


    private static Layer[] initializeHidden(int inputSize, int[] hiddenSize) {
        Layer[] hidden = new LogisticLayer[hiddenSize.length];
        int currSize = inputSize;
        for (int i = 0; i < hidden.length; i++) {
            hidden[i] = new LogisticLayer(hiddenSize[i], currSize);
            currSize = hiddenSize[i];
        }
        return hidden;
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
    public Double[] predict(Double[] inputs) {
        Double[] y = inputs;
        // pass the inputs through the hidden layers
        for (Layer l : hidden) {y = l.predict(y);}

        // pass the inputs through the output layer
        y = output.predict(y);
        return y;
    }


    @Override
    public Double[] train(Double[] inputs, Double[] outputs, int iterations) {
        // set learning rate for the hidden layers
        for (Layer layer : hidden) {layer.setLearningRate(l.rate(iterations));}

        // set learning rate for the output layer
        output.setLearningRate(l.rate(iterations));

        // make a prediction using the current model
        Double[] y = predict(inputs);

        // calculate the signal using the outputs
        Double[] signals = DoubleOps.subtract(outputs, y);

        // output layer: update the weights and pass on the signals
        signals = output.update(signals);

        // hidden layers: update the weights and pass on the signals
        for (int i = hidden.length-1; i > 0; i--) {
            Layer l = hidden[i];
            signals = l.update(signals);
        }

        return signals;
    }


    @Override
    public void train(DataFrame<Double> df) {
        List<Double[]> inputs = df.inputs();
        List<Double[]> outputs = df.outputs();
        for (int i = 0; i < df.size(); i++) {
            train(inputs.get(i), outputs.get(i), i);
        }
    }


    @Override
    public int size() {return hidden.length;}


    @Override
    public String toString() {
        String out = "]";
        for (int i = 0; i < hidden.length+1; i++) {out += "layer " + (i+1) + ": " + hidden[i].toString() +"\n";}
        out += "layer " + (hidden.length+1) + ": " + output.toString() + "]";
        return out;
    }


}
