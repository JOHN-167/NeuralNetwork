package src.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * An interface of vector operation methods
 */
public interface DoubleOps {
    
    /**
     * The usual dot product of two vectors A and B
     * @return the dot product
     * @throws IllegalArgumentException
     */
    public static Double dotP(Double[] vectorA, Double[] vectorB) throws IllegalArgumentException {
        if (vectorA.length != vectorB.length) {throw new IllegalArgumentException();}
        Double out = 0.0;
        for (int i = 0; i < vectorA.length; i++) {out += vectorA[i] * vectorB[i];}
        return out;
    }

    /**
     * Return an array of the desired size whose entries are
     * random Doubles 
     * @param size the size of the array
     * @return a random Double array
     */
    public static Double[] random(int size) {
        Random rd = new Random();
        Double[] out = new Double[size];
        for (int i = 0; i < size; i++) {out[i] = rd.nextDouble();}
        return out;
    }
    
    /**
     * Return the sum of two vectors
     * @return the sum vector
     * @throws IllegalArgumentException
     */
    public static Double[] sum(Double[] vectorA, Double[] vectorB) throws IllegalArgumentException {
        if (vectorA.length != vectorB.length) {throw new IllegalArgumentException();}
        Double[] out = new Double[vectorA.length];
        for (int i = 0; i < vectorA.length; i++) {out[i] = vectorA[i] + vectorB[i];}
        return out;   
    }
    
    /**
     * Return the difference of two vectors
     * @return the difference vector
     * @throws IllegalArgumentException
     */
    public static Double[] subtract(Double[] vectorA, Double[] vectorB) throws IllegalArgumentException {
        if (vectorA.length != vectorB.length) {throw new IllegalArgumentException();}
        Double[] out = new Double[vectorA.length];
        for (int i = 0; i < vectorA.length; i++) {out[i] = vectorA[i] - vectorB[i];}
        return out;   
    }

    /**
     * Return the value of the sigmoid (logistic) function in the form
     * y = 1 / (1 + e^(-x))
     * @param x the input to the sigmoid function
     * @return the output of the sigmoid function
     */
    public static Double sigmoid(Double x) {return 1.0 / (1 + Math.exp(-x));}


    /**
     * Reduce the vector array into the sum of its elements
     */
    public static Double reduce(Double[] in) {
        Double sum = 0.0;
        for (Double i : in) {sum += i;}
        return sum;
    }


    /**
     * Join two arrays into one large arrays
     */
    public static Double[] join(Double[] vectorA, Double[] vectorB) {
        Double[] out = new Double[vectorA.length+vectorB.length];
        for (int i = 0; i < vectorA.length; i++) {out[i] = vectorA[i];}
        for (int i = 0; i < vectorB.length; i++) {out[i+vectorA.length] = vectorB[i];}
        return out;        
    }


    /**
     * Join two lists of arrays
     */
    public static List<Double[]> join(List<Double[]> listA, List<Double[]> listB) throws IllegalArgumentException {
        if (listA.size() != listB.size()) {throw new IllegalArgumentException();}
        List<Double[]> out = new ArrayList<>();
        for (int i = 0; i < listA.size(); i++) {out.add(join(listA.get(i), listB.get(i)));}
        return out;
    }


    /**
     * Check if two vectors are identical
     */
    public static boolean equals(Double[] vectorA, Double[] vectorB) {
        if (vectorA.length != vectorB.length) {return false;}
        for (int i = 0; i < vectorA.length; i++) {
            if (!vectorA[i].equals(vectorB[i])) {return false;}
        }
        return true;
    }


    /**
     * Create a list of size-1 vectors from a list of numbers
     */
    public static List<Double[]> toArray(List<Double> list) {
        List<Double[]> out = new ArrayList<>();
        for (Double d : list) {out.add(new Double[]{d});}
        return out;
    }

}
