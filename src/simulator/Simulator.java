package src.simulator;

import java.util.List;
import java.util.Map;

import src.base.LinearClassifier;
import src.base.LogisticClassifier;
import src.base.MultiLayerNN;
import src.base.PercentageScorer;
import src.base.SingleLayerNN;
import src.core.DoubleScorer;
import src.core.Layer;
import src.core.NeuralNetwork;
import src.core.Perceptron;
import src.ls.LearningSchedule;
import src.reader.DataFrame;
import src.util.DoubleOps;

public class Simulator {

    /**
     * Stores testing and training data sets
     */
    private Map<Integer, DataFrame<Double>> splits;


    /**
     * The size of inputs to the model
     */
    private int inputSize;


    /**
     * The size of outputs to the model. This is only used for non-perceptron models.
     * The default value is set to 1.
     */
    private int outputSize = 1;


    /**
     * The learning schedule
     */
    private LearningSchedule l;
    
    
    /**
     * Constructor that creates the simulator. Since no ouput size is provided, the
     * default value of 1 is used. This is recommended for perceptron-only models.
     */
    public Simulator(Map<Integer, DataFrame<Double>> splits, int inputSize, LearningSchedule l) {
        this.splits = splits;
        this.inputSize = inputSize;
        this.l = l;
    }
    

    /**
     * Constructor that creates the simulator. 
     * This is recommended for non-perceptron models.
     */
    public Simulator(Map<Integer, DataFrame<Double>> splits, int inputSize, int outputSize, LearningSchedule l) {
        this(splits, inputSize, l);
        this.outputSize = outputSize;
    }


    public void linear_perceptron(int iteration, double threshold) {
        // create a neural network
        Perceptron perceptron = new LinearClassifier(inputSize, threshold);
        
        // run simulation
        perceptron(perceptron, iteration);
    }
    
    
    public void logistic_perceptron(int iteration) {
        // create a neural network
        Perceptron perceptron = new LogisticClassifier(inputSize);
        
        // run simulation
        perceptron(perceptron, iteration);
    }
    
    
    private void perceptron(Perceptron perceptron, int iteration) {        
        // set learning schedule
        perceptron.setLearningSchedule(l);
        
        // training
        DataFrame<Double> train = splits.get(0);
        for (int i = 0; i < iteration; i++) {
            perceptron.train(train);
        }
        
        // testing
        DataFrame<Double> test = splits.get(1);        
        List<Double> test_pred = perceptron.predict(test.inputs());
        
        DataFrame<Double> pred = new DataFrame<>(DoubleOps.join(test.outputs(), DoubleOps.toArray(test_pred)), test.getOutputSize());
        
        // scoring
        DoubleScorer scorer = new PercentageScorer(pred);
        System.out.println(scorer.score()+"/"+test.size());
    }


    /**
     * A helper function that simulates training on a neural network and print out the result
     * of a prediction on the model. The model is trained using the training data set for a number
     * of iterations. The model in use is a multilayer model
     * @param splits a split of training and testing data set
     * @param iteration the number of times the model is trained
     */
    public void multilayer(Layer[] hidden, int iteration, double threshold) {

        // create a neural network
        NeuralNetwork net = new MultiLayerNN(inputSize, hidden, outputSize, threshold, l);

        // training
        DataFrame<Double> train = splits.get(0);
        for (int i = 0; i < iteration; i++) {
            net.train(train);
        }
        
        // testing
        DataFrame<Double> test = splits.get(1);        
        List<Double[]> test_pred = net.predict(test.inputs());
        
        DataFrame<Double> pred = new DataFrame<>(DoubleOps.join(test.outputs(), test_pred), test.getOutputSize());
        
        // scoring
        DoubleScorer scorer = new PercentageScorer(pred);
        System.out.println(scorer.score()+"/"+test.size());

    }


    /**
     * A helper function that simulates training on a neural network and print out the result
     * of a prediction on the model. The model is trained using the training data set for a number
     * of iterations. The model in use is a singlelayer model
     * @param splits a split of training and testing data set
     * @param iteration the number of times the model is trained
     */
    public void singlelayer(int iteration, double threshold) {
        
        // create a neural network
        NeuralNetwork net = new SingleLayerNN(outputSize, inputSize, l, threshold);
        
        // training
        DataFrame<Double> train = splits.get(0);
        for (int i = 0; i < iteration; i++) {
            net.train(train);
        }
        
        // testing
        DataFrame<Double> test = splits.get(1);        
        List<Double[]> test_pred = net.predict(test.inputs());
        
        DataFrame<Double> pred = new DataFrame<>(DoubleOps.join(test.outputs(), test_pred), test.getOutputSize());
        
        // scoring
        DoubleScorer scorer = new PercentageScorer(pred);
        System.out.println(scorer.score()+"/"+test.size());

    }

}
