package reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataFrame<E> extends ArrayList<E[]> {


    /**
     * The input size of the input part of the Record
     */
    private int inputSize;


    // constructors    
    public DataFrame(int inputSize) {
        super();
        this.inputSize = inputSize;
    }

    public DataFrame(Collection<E[]> c, int inputSize) {
        super(c);
        this.inputSize = inputSize;
    }


    /**
     * Return the input part of the Record
     */
    public List<E[]> inputs() {
        List<E[]> out = new ArrayList<>();
        for (E[] e : this) {
            out.add(Arrays.copyOf(e, inputSize));
        }
        return out;
    }


    /**
     * Return the output part of the Record
     */
    public List<E[]> outputs() {
        List<E[]> out = new ArrayList<>();
        for (E[] e : this) {
            out.add(Arrays.copyOfRange(e, inputSize, this.get(0).length));
        }
        return out;
    }

    
    @Override
    public String toString() {
        String out = "DataFrame: " + "count = " + size() + ", df  = [";
        for (int i = 0; i < size(); i++) {
            E[] e = this.get(i);
            out += String.format("\n[%d] {inputs = ", i+1) + 
                Arrays.toString(Arrays.copyOf(e, inputSize));
            out += ", outputs = " + 
                Arrays.toString(Arrays.copyOfRange(e, inputSize, e.length));
            out += "}";
        }
        out += "]";
        return out;
    }

    /**
     * Return input size
     */
    public int getInputSize() {
        return inputSize;
    }


    /**
     * Return the Record size
     */
    public int getOutputSize() {
        return this.get(0).length - inputSize;
    } 


    /**
     * Set input size
     */
    public void setInputSize(int inputSize) {this.inputSize = inputSize;}


    // Randomize retrieval for a K-split

    /**
     * Returns a map that splits the Records
     */
    public Map<Integer, DataFrame<E>> split(int[] splits) throws IllegalArgumentException {
        if (Arrays.stream(splits).sum() != size()) {
            throw new IllegalArgumentException();    
        }
        Map<Integer, DataFrame<E>> out = new HashMap<>();
        UniqueRandom rd = new UniqueRandom(size());
        for (int i = 0; i < splits.length; i++) {
            out.put(i, random(splits[i], rd));
        }
        return out;
    }


    /**
     * Return a random subRecords given a UniqueRandom
     */
    private DataFrame<E> random(int size, UniqueRandom rd) {
        List<E[]> out = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            out.add(this.get(rd.nextInt()));
        }
        return new DataFrame<>(out, inputSize);
    }


    /**
     * Return a random subRecords given a UniqueRandom
     */
    public DataFrame<E> random(int size) {
        return this.random(size, new UniqueRandom(size()));
    }


    /**
     * Return the first line of the DataFrame without removing
     */
    public E[] peek() {
        try {
            return this.get(0);
        } catch (Exception e) {
            System.out.println("Empty DataFrame");
            return null;
        }
    }


    // parseDouble operations: converting a String DataFrame to Double DataFrame
     /**
     * Returns a Double DataFrame from a String DataFrame
     */
    public static DataFrame<Double> parseDouble(DataFrame<String> in) {

        // determine if the column is a double
        String[] firstLine = in.peek();
        DataFrame<Double> out = new DataFrame<>(in.getInputSize());

        boolean[] isDouble = new boolean[firstLine.length];
        int i = 0;
        for (String str : firstLine) {isDouble[i++] = isDouble(str);}

        // for the non-Double Strings, return all its unique values
        Map<Integer, List<String>> uniques = unique(in, isDouble);

        // add the corresponding Double for each String
        for (String[] r : in) {
            parseDouble(out, r, isDouble, uniques);
        }
        out.setInputSize(newInputSize);
        return out;
    }

    private static int newInputSize;

    /**
     * Retunrs map from index to a list of unique Strings
     * @param in the String DataFrame
     * @param isDouble a boolean array indicates which columns are Double
     */
    private static void parseDouble(DataFrame<Double> df,
                                    String[] in, boolean[] isDouble, 
                                    Map<Integer, List<String>> uniques) {
        int inputSize = df.getInputSize();
        List<Double> out = new ArrayList<>();

        for (int j = 0; j < in.length; j++) {
            // if the String is a Double, parse
            if (isDouble[j]) {
                out.add(Double.parseDouble(in[j]));
            }

            // if the String is not a Double, replace it with dummies
            else {
                // the number of dummies is the number of unique Strings for that index
                double[] dummies = new double[uniques.get(j).size()];

                // find where the String is among the dummies 
                dummies[uniques.get(j).indexOf(in[j])] = 1.0;
                for(double d : dummies) {
                    out.add(d);
                }

                // if the String being investigated is in the input, increase input size
                if(j < df.getInputSize()) {inputSize += dummies.length-1;}
            }
        }        
        newInputSize = inputSize;
        df.add(convert(out));
    }


    /**
     * Helper function
     */
    private static Double[] convert(List<Double> c) {
        int i = 0;
        Double[] out = new Double[c.size()];
        for (Double e : c) {out[i++] = e;}
        return out;
    }

    
    /**
     * Retunrs map from index to a list of unique Strings
     * @param in the String DataFrame
     */
    public static Map<Integer, List<String>> unique(DataFrame<String> in) {
        String[] firstLine = in.peek();
        boolean[] isDouble = new boolean[firstLine.length];
        int i = 0;
        for (String str : firstLine) {isDouble[i++] = isDouble(str);}
        return unique(in, isDouble);

    }

    
    /**
     * Retunrs map from index to a list of unique Strings
     * @param in the String DataFrame
     * @param isDouble a boolean array indicates which columns are Double
     */
    private static Map<Integer, List<String>> unique(DataFrame<String> in, boolean[] isDouble) {
        Map<Integer, List<String>> out = new HashMap<>();
        for (int i = 0; i < isDouble.length; i++) {
            if (!isDouble[i]) {
                Set<String> uniques = new HashSet<>();

                // add the String at index i to the Set uniques
                for (String[] r : in) {uniques.add(r[i]);}
                
                // put the record in the map
                out.put(i, new ArrayList<>(uniques));
            }
        }
        return out;
    }

    
    /**
     * Returns true if the String is a double
     */
    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception e) {return false;}
    }

}
