package core;

import java.util.List;

interface Predictor<T> {
        
    /**
     * The Predictor uses the inputs to predict the most likely
     * outputs for the model
     * @param inputs the inputs received by the Perceptron
     * @return the output of the Perceptron
     */
    public T predict(Double[] inputs);


    /**
     * Predict the model from a list of inputs
     * @param df
     */
    public List<T> predict(List<Double[]> inputs);


}