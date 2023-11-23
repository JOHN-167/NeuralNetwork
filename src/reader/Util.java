package reader;

import java.util.List;

public class Util<T> {
    
    public static double[] toDoubleArray(List<Double> in) {
        double[] out = new double[in.size()];
        for (int i = 0; i < in.size(); i++) {out[i] = in.get(i);}
        return out;
    }


}
