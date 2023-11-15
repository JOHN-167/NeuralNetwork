package src;

public class LogisticLayer extends AbstractLayer {    

    public LogisticLayer(int size) {
        super(size);
        for (int i = 0; i < size; i++) {
            nodes[i] = new LogisticPerceptron(size);
        }
    }

}
