package src.io;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

class UniqueRandom {

    /**
     * Random number generator using the default Random class
     */
    private Random rd = new Random();
    
    /**
     * The set of unique Random double numbers
     */
    private Deque<Double> doubleStack;
    
    /**
     * The set of unique Random double numbers
     */
    private Deque<Integer> intStack;

    /**
     * A constructor to create a set of unique doubles with a 
     * desired size
     * @param range the range of possible numbers
     */
    public UniqueRandom(int range) {
        // initialize doubleStack
        doubleStack = new ArrayDeque<>();
        while (doubleStack.size() < range) {
            Double random = rd.nextDouble(range);
            if (!doubleStack.contains(random)) {doubleStack.add(random);}
        }
        
        // initialize intStack
        intStack = new ArrayDeque<>();
        while (intStack.size() < range) {
            Integer random = rd.nextInt(range);
            if (!intStack.contains(random)) {intStack.add(random);}
        }
        // System.out.println(intStack.toString());
    }

    /**
     * Return a random double from the unique list
     */
    public double nextDouble() {return doubleStack.pop();}

    /**
     * Return a random double from the unique list
     */
    public int nextInt() {return intStack.pop();}


}
