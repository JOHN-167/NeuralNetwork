package src.core;

import src.ls.LearningSchedule;

public interface Perceptron extends Predictor<Double>, Trainable<Double> {

    
    /**
     * The Perceptron receives a signal and evaluates and updates
     * the weight space
     * @param signal the signal
     * @return signals for each input
     */
    public Double[] update(Double signal);


    /**
     * The Perceptron learning rule
     * @param p the input of the rule
     * @return the output of the rule
     */
    public Double learningRule(Double p);


    /**
     * The derivative of the learning rule that is assumes to be
     * able to be calculated from the output alone.
     * @param p the input of the reverse rule
     * @return the output of the reverse rule
     */
    public Double reverseLearningRule(Double p);


    /**
     * Update the learning rate of the Perceptron
     * @param learningRate the new learning rate
     */
    public void setLearningRate(Double learningRate);


    /**
     * Set learning schedule for the perceptron. Used when 
     * the perceptron is its an independent model
     * @param ls the learning schedule
     */
    public void setLearningSchedule(LearningSchedule ls);


    /**
     * Return the number of inputs (size) of the Perceptron
     */
    public int size();


    @Override
    public String toString();

}
