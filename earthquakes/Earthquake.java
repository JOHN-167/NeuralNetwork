package earthquakes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

import ls.DecayingLS;
import reader.DataFrame;
import reader.FileReader;
import util.Simulator;

public class Earthquake {
    
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("earthquakes\\earthquake-noisy.data.txt");
        
        // create the reader and read the file
        int inputSize = 2;
        DataFrame<Double> df = (new FileReader(file)).readDouble_csv(inputSize);
        
        // split the dataset into train set (100 records) and test set (50 records)
        double trainPercent = 0.8;
        int trainSize = (int) (trainPercent*df.size());
        int testSize = df.size()-trainSize;
        Map<Integer, DataFrame<Double>> splits = df.split(new int[]{trainSize,testSize});

        // create a simulator
        Simulator op = new Simulator(splits, 2, new DecayingLS(0.3));

        // perform some simulations to test the model
        for (int i = 0; i < 10; i++) {
            op.linear_perceptron(100, 0);
        }

    }





}
