package src.core;

public interface Layer extends Predictor<Double[]> {
    
    /**
     * Populate the weight space of each Perceptron in the layer
     * with random Doubles
     * @param inputSize the size of each Perceptron
     */
    public void initializeWeights(int inputSize);


    /**
     * The layer receives a vector of signals and evaluates and updates
     * the weight space of all its Perceptron units
     * @param signals the vector of signals
     * @return signals for each input
     */
    public Double[] update(Double[] signals);

    /**
     * Update the learning rate of the neural network
     * @param learningRate the new learning rate
     */
    public void setLearningRate(Double learningRate);

    /**
     * Return the size of the layer
     */
    public int size();

    @Override
    public String toString();

}
