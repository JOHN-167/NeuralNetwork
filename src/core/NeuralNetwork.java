package src.core;

import src.ls.LearningSchedule;

public interface NeuralNetwork extends Trainable<Double[]>, Predictor<Double[]> {
    

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
