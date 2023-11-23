package iris;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import core.Layer;
import ls.DecayingLS;
import base.LogisticLayer;
import reader.DataFrame;
import reader.FileReader;
import util.Simulator;


public class Iris {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("iris\\iris.data.txt");

        // create the reader and read the file
        int inputSize = 4;
        DataFrame<Double> df = (new FileReader(file)).readDouble_csv(inputSize);
        
        // split the dataset into train set (100 records) and test set (50 records)
        double trainPercent = 0.8;
        int trainSize = (int) (trainPercent*df.size());
        int testSize = df.size()-trainSize;
        Map<Integer, DataFrame<Double>> splits = df.split(new int[]{trainSize,testSize});
        
        // build hidden layers
        Layer[] hidden = new Layer[]{
            new LogisticLayer(3, 4),
            new LogisticLayer(2, 3),
            new LogisticLayer(3, 2)
        };

        // create a simulator
        Simulator op = new Simulator(splits, 4, 3, new DecayingLS(0.3));

        // perform some simulations to test the model
        for (int i = 0; i < 10; i++) {
            op.multilayer(hidden, 100, 0.5);
        }

    }


    
    
}
