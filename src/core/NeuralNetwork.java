package core;

import java.util.List;

import ls.LearningSchedule;

public interface NeuralNetwork extends Trainable, Predictor<Double[]> {
    

    /**
     * Predict the model from a list of inputs
     * @param df
     */
    public List<Double[]> predict(List<Double[]> inputs);


    /**
     * Update the learning rate of the neural network
     * @param learningRate the new learning rate
     */
    public void setLearningSchedule(LearningSchedule schedule);


    /**
     * Return the size of the neural network
     */
    public int size();


    @Override
    public String toString();


}
