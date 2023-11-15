package src;

public class LogisticPerceptron extends Perceptron {

    public LogisticPerceptron(int inputSize) {
        super(inputSize);
    }

    @Override
    public double eval(double input) {
        return 1/(1+Math.exp(-input)); // logistics function
    }

    public void train(double[] inputs, double output) {
        predict(inputs);
        assert inputs.length == size;
        double error = output - this.output;
        double grad = this.output*(1-this.output); // gradient of activation function
        for (int i = 0; i < size; i++) {
            W[i] += l*error*grad*inputs[i]; 
        }
    }

    
}
