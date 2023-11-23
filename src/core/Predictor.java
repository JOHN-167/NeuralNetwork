package core;

interface Predictor<T> {
        
    /**
     * The Predictor uses the inputs to predict the most likely
     * outputs for the model
     * @param inputs the inputs received by the Perceptron
     * @return the output of the Perceptron
     */
    public T predict(Double[] inputs);

}