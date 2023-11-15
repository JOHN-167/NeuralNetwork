package src;

abstract public class NeuralNetwork implements Trainable<double[]> {
    
    protected AbstractLayer[] layers;
    protected int size;

    public NeuralNetwork(int size) {
        this.size = size;
        this.layers = new AbstractLayer[size];
    }

    public double[] predict(double[] inputs) {
        double[] out = inputs; 
        for (int i = 0; i < size; i++) {
            out = layers[i].predict(inputs);
        }
        return out;
    }

    @Override
    public void train(double[] inputs, double[] outputs) {
        assert outputs.length == layers[size-1].size();
        double[] signals = predict(inputs);
        for (int i = 0; i < outputs.length; i++) {
            signals[i] = outputs[i] - signals[i];
        }
        for (int i = 0; i < size; i++) {
            signals = layers[i].train_hidden(inputs, signals);
        }
    }

}
