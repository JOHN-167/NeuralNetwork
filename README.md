# NeuralNetwork

## Build and Run the Program

### Build the program (file compilation)
- javac src\util\\*.java
- javac src\reader\\*.java
- javac src\ls\\*.java
- javac src\core\\*.java
- javac src\base\\*.java
- javac src\simulator\\*.java

### Run the program (run file):
java MainClass filepath inputsize outputsize trainpercent type trials threshold

- inputsize (int): the size of the input
- outputsize (int): the size of the output
- trainpercent (double): the percentage of the data set used for training ([0,1])
- type (int): 1 (Linear Perceptron), 2 (Logistic Perceptron), 3 (SingleLayered Perceptron)
- trials (int): the number of times the model is trained
- threshold (double): for models that require a threshold, this is the threshold