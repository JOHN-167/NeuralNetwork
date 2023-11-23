package bn.src.base;

import bn.src.core.AbstractLayer;

public class LinearLayer extends AbstractLayer {
    

    /**
     * A constructor that specifies the size of the single layer of
     * the neural network and the cutoff threshold
     * @param size the size of the layer
     * @param inputSize the size of the inputs (Perceptron)
     * @param threshold the cutoff threshold
     */
    public LinearLayer(int size, int inputSize, Double threshold) {
        super(size, inputSize);
        setThreshold(threshold);
    }


    /**
     * A constructor that specifies the size of the single layer of
     * the neural network and set the cutoff threshold to 0
     * @param size the size of the layer
     * @param inputSize the size of the inputs (Perceptron)
     */
    public LinearLayer(int size, int inputSize) {super(size, inputSize);}


    public void setThreshold(Double threshold) {
        for (int i = 0; i < size(); i++) {((LinearClassifier) units[i]).setThreshold(threshold);} 
    }


    @Override
    public void initializeWeights(int inputSize) {
        this.inputSize = inputSize;
        for (int i = 0; i < size(); i++) {units[i] = new LinearClassifier(inputSize);}
    }
  
}
