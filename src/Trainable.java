package src;

public interface Trainable<T> {

    public void train(double[] inputs, T outputs);
    
}