package bn.src.base;

import bn.src.core.AbstractLayer;

public class LogisticLayer extends AbstractLayer {
    /**
     * A constructor that specifies the size of the single layer of
     * the neural network
     * @param size
     */
    public LogisticLayer(int size, int inputSize) {
        super(size, inputSize);
    }
    

    @Override
    public void initializeWeights(int inputSize) {
        this.inputSize = inputSize;
        for (int i = 0; i < size(); i++) {units[i] = new LogisticClassifier(inputSize);}
    }


}

