package src;

public class LinearPerceptron extends Perceptron {

    private double threshold;

    public LinearPerceptron(int inputSize, double threshold) {
        super(inputSize);
        this.threshold = threshold;
        this.size = inputSize;
    }

    public double eval(double input) {
        return (input > threshold)? 1 : 0;
    }

    public void train(double[] inputs, double output) {
        assert inputs.length == size;
        double error = output - this.output;
        for (int i = 0; i < size; i++) {
            W[i] += l*error*inputs[i]; 
        }
    }
    
}
