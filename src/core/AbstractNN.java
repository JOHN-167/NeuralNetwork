package src.core;

import java.util.ArrayList;
import java.util.List;

import src.io.DataFrame;
import src.ls.LearningSchedule;

public abstract class AbstractNN implements NeuralNetwork {


    /**
     * The output layer of the multi-layered neural network
     */
    protected Layer output;


    /**
     * The learning schedule for the neural network
     */
    protected LearningSchedule l;
    

    @Override
    public List<Double[]> predict(List<Double[]> inputs) {
        List<Double[]> out = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
           out.add(predict(inputs.get(i))); 
        }
        return out;
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
    public void setLearningSchedule(LearningSchedule schedule) {l = schedule;}


}
