import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import src.base.LogisticLayer;
import src.core.Layer;
import src.ls.DecayingLS;
import src.reader.DataFrame;
import src.reader.FileReader;
import src.simulator.Simulator;

public class MainClass {
    
    public static void main(String[] args) throws FileNotFoundException {
        // create file
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
            new LogisticLayer(2, 2),
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
