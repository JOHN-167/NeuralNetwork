package base;

import core.AbstractNN;
import ls.LearningSchedule;
import util.DoubleOps;

/**
 * A neural network with a single layer of Perceptrons
 */
public class SingleLayerNN extends AbstractNN {


    /**
     * A constructor that specifies the size of the single layer of
     * the neural network and the cutoff threshold
     * @param size the size of the layer
     * @param inputSize the size of the inputs (Perceptron)
     * @param threshold the cutoff threshold
     */
    public SingleLayerNN(int size, int inputSize, LearningSchedule l, Double threshold) {
        output = new LinearLayer(size, inputSize, threshold);
        this.l = l;
    }


    /**
     * A constructor that specifies the size of the single layer of
     * the neural network and set the cutoff threshold to 0
     * @param size the size of the layer
     * @param inputSize the size of the inputs (Perceptron)
     */
    public SingleLayerNN(int size, int inputSize, LearningSchedule l) {
        this(size, inputSize, l, 0.0);
    }


    @Override
    public Double[] predict(Double[] inputs) {
        return output.predict(inputs);
    }


    @Override
    public int size() {return output.size();}


    @Override
    public Double[] train(Double[] inputs, Double[] outputs, int iterations) {
        // set learning rate for the output layer
        output.setLearningRate(l.rate(iterations));

        // make a prediction using the current model
        Double[] y = predict(inputs);

        // calculate the signal using the outputs
        Double[] signals = DoubleOps.subtract(outputs, y);

        // output layer: update the weights and pass on the signals
        return output.update(signals);
    }

}
