package bn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import bn.src.base.LogisticLayer;
import bn.src.core.Layer;
import bn.src.ls.DecayingLS;
import bn.src.reader.DataFrame;
import bn.src.reader.FileReader;
import bn.src.util.Simulator;

public class MainClass {
    
    public static void main(String[] args) throws FileNotFoundException {

        /**
         * java MainClass filepath inputsize outputsize trainpercent type trials threshold
         * 
         * - inputsize (int): the size of the input
         * - outputsize (int): the size of the output
         * - trainpercent (double): the percentage of the data set used for training ([0,1])
         * - type (int): 1 (Linear Perceptron), 2 (Logistic Perceptron), 3 (SingleLayered Perceptron)
         * - trials (int): the number of times the model is trained
         * - threshold (double): for models that require a threshold, this is the threshold
         */
        
        File file = new File(args[0]);
        
        // create the reader and read the file
        int inputSize = Integer.parseInt(args[1]);
        int outputsize = Integer.parseInt(args[2]);
        DataFrame<Double> df = (new FileReader(file)).readDouble_csv(inputSize);
        
        // split the dataset into train set (100 records) and test set (50 records)
        double trainPercent = Double.parseDouble(args[3]);
        int trainSize = (int) (trainPercent*df.size());
        int testSize = df.size()-trainSize;
        Map<Integer, DataFrame<Double>> splits = df.split(new int[]{trainSize,testSize});

        // create a simulator
        Simulator op = new Simulator(splits, inputSize, outputsize, new DecayingLS(0.3));

        // perform some simulations to test the model
        int type = Integer.parseInt(args[4]);
        int trials = Integer.parseInt(args[5]);
        double threshold = Double.parseDouble(args[6]);

        // build hidden layers
        Layer[] hidden = new Layer[]{
            new LogisticLayer(3, inputSize),
            new LogisticLayer(2, 3),
            new LogisticLayer(outputsize, 2),
        };

        for (int i = 0; i < 10; i++) {
            switch (type) {
                case 1:
                    op.linear_perceptron(trials, threshold);
                    break;
            
                case 2:
                    op.logistic_perceptron(trials);
                    break;
            
                case 3:
                    op.singlelayer(trials, threshold);
                    break;
            
                case 4:
                    op.multilayer(hidden, trials, threshold);
                    break;
            
                default:
                    break;
            }
        }

    }

}
