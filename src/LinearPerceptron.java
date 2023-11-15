package src;

public class LinearPerceptron extends AbstractPerceptron {

    private double threshold;

    public LinearPerceptron(int inputSize, double threshold) {
        super(inputSize);
        this.threshold = threshold;
        this.size = inputSize;
    }

    public double eval(double input) {
        return (input > threshold)? 1 : 0;
    }
    
    public void train_hidden(double[] inputs, double errors) {
        for (int i = 0; i < size; i++) {
            W[i] += l*errors*inputs[i]; 
        }
    }

}
