package src;

public class LogisticPerceptron extends AbstractPerceptron {

    public LogisticPerceptron(int inputSize) {
        super(inputSize);
    }

    @Override
    public double eval(double input) {
        return 1/(1+Math.exp(-input)); // logistics function
    }

    @Override
    public void train_hidden(double[] inputs, double errors) {
        double grad = this.output*(1-this.output); // gradient of activation function
        for (int i = 0; i < size; i++) {
            W[i] += l*errors*grad*inputs[i]; 
        }
    }
    
}
