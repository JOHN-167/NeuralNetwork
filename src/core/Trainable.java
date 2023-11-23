package core;

import reader.DataFrame;

/**
 * A Trainable class uses the inputs to predict the most likely
 * outputs for the model and compares the prediction to the 
 * desired outputs to update the weight space accordingly 
 */
interface Trainable<E> {
    
    
    /**
     * Train the model using an array of inputs and outputs
     * @param inputs the inputs received by the neural network
     * @param outputs the desired outputs
     * @return the signals from the updating process
     */
    public Double[] train(Double[] inputs, E outputs, int iterations);


    
    /**
     * Train the model using a Dataframe
     * @param df the DataFrame
     */
    public void train(DataFrame<Double> df);

}
