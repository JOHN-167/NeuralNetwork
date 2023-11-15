package src;

abstract public class AbstractLayer implements Trainable<double[]> {
    
    protected AbstractPerceptron[] nodes;
    protected int size;

    public AbstractLayer(int size) {
        this.size = size;
        this.nodes = new AbstractPerceptron[size];         
    }

    public double[] predict(double[] inputs) {
        double[] out = new double[size];
        for (int i = 0; i < size; i++) {
            out[i] = nodes[i].predict(inputs);
        }
        return out;
    }

    @Override
    public void train(double[] inputs, double[] outputs) {
        assert outputs.length == size;
        for (int i = 0; i < size; i++) {
            nodes[i].train(inputs, outputs[i]);
        }
    }

    public double[] train_hidden(double[] inputs, double[] signals) {
        assert signals.length == size;
        for (int i = 0; i < size; i++) {
            nodes[i].train_hidden(inputs, signals[i]);
        }
        signals = new double[size];
        return signals;
    }

    public int size() {
        return size;
    }

}
