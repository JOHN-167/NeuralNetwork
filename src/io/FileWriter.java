package src.io;

import java.io.File;
import java.io.IOException;

public class FileWriter {
    
    private DataFrame<String> df;

    
    public FileWriter(DataFrame<String> df) {this.df = df;}


    public void writeToCSV(String filename) throws IOException {
        java.io.FileWriter writer = new java.io.FileWriter(filename);
        for (String[] r : df) {
            String txt = "";
            for (String i : r) {txt += i+",";}
            txt = txt.substring(0, txt.length()-1) + "\n";
            writer.append(txt);
        }
        writer.flush();
        writer.close();
    }


    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\nguye\\Downloads\\NeuralNetwork" +
                            "\\examples\\iris\\iris.data.txt");
        FileReader reader = new FileReader(file);
        DataFrame<String> df = reader.read_csv(0);
        FileWriter writer = new FileWriter(df);
        writer.writeToCSV("test.txt");
    }


}
