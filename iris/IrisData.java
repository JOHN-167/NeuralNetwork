package iris;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import core.DoubleScorer;
import core.Layer;
import core.NeuralNetwork;
import ls.DecayingLS;
import base.LogisticLayer;
import base.MultiLayerNN;
import base.PercentageScorer;
import base.SingleLayerNN;
import reader.DataFrame;
import reader.FileReader;
import util.VectorOps;


public class IrisData {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("iris\\iris.data.txt");
        
        // create the reader and read the file
        int inputSize = 4;
        DataFrame<Double> df = (new FileReader(file)).readDouble_csv(inputSize);
        
        // split the dataset into train set (100 records) and test set (50 records)
        Map<Integer, DataFrame<Double>> splits = df.split(new int[]{100,50});

        // perform some simulations to test the model
        for (int i = 0; i < 10; i++) {
            simulate_multilayer(splits, 1000);
        }

    }

    public static void simulate_multilayer(Map<Integer, DataFrame<Double>> splits, int iteration) {

        // create a neural network
        NeuralNetwork net = new MultiLayerNN(4, new Layer[]{
            new LogisticLayer(3, 4),
            new LogisticLayer(2, 3),
            new LogisticLayer(3, 2)
            }, 3, 0.5, new DecayingLS(0.1));

        // training
        DataFrame<Double> train = splits.get(0);
        for (int i = 0; i < iteration; i++) {
            net.train(train);
        }
        
        // testing
        DataFrame<Double> test = splits.get(1);        
        List<Double[]> test_pred = net.predict(test.inputs());
        
        DataFrame<Double> pred = new DataFrame<>(VectorOps.join(test.outputs(), test_pred), test.getOutputSize());
        
        // scoring
        DoubleScorer scorer = new PercentageScorer(pred);
        System.out.println(scorer.score()+"/"+test.size());

    }


    public static void simulate_singlelayer(Map<Integer, DataFrame<Double>> splits, int iteration) {
        
        // create a neural network
        NeuralNetwork net = new SingleLayerNN(3, 4, new DecayingLS(0.3), 0.3);
        
        // training
        DataFrame<Double> train = splits.get(0);
        for (int i = 0; i < iteration; i++) {
            net.train(train);
        }
        
        // testing
        DataFrame<Double> test = splits.get(1);        
        List<Double[]> test_pred = net.predict(test.inputs());
        
        DataFrame<Double> pred = new DataFrame<>(VectorOps.join(test.outputs(), test_pred), test.getOutputSize());
        
        // scoring
        DoubleScorer scorer = new PercentageScorer(pred);
        System.out.println(scorer.score()+"/"+test.size());

    }
    
}
