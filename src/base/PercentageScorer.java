package src.base;

import java.util.List;

import src.core.DoubleScorer;
import src.io.DataFrame;
import src.util.DoubleOps;

public class PercentageScorer extends DoubleScorer {

    /**
     * Constructor
     */
    public PercentageScorer(DataFrame<Double> df) {super(df);}


    @Override
    public int score() {
        List<Double[]> inputs = df.inputs();
        List<Double[]> outputs = df.outputs();
        int size = df.size();
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            if (DoubleOps.equals(inputs.get(i), outputs.get(i))) {cnt++;}
        }
        return cnt;
    }


    
}
