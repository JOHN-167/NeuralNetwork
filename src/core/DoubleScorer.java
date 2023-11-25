package src.core;

import src.reader.DataFrame;

abstract public class DoubleScorer {

    protected DataFrame<Double> df;

    /**
     * 
     */
    public DoubleScorer(DataFrame<Double> df) {
        this.df = df;
    }
    
    /**
     * Return a score for how fit the prediction is 
     */
    abstract public int score();


}
