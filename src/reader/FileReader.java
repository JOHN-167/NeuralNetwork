package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Read CSV file backed by a Scanner
 */
public class FileReader {

    private Scanner sc;

    /**
     * A constructor that assigns the Scanner to read the file.
     * @param file the file to be read
     * @throws FileNotFoundException
     */
    public FileReader(File file) throws FileNotFoundException {sc = new Scanner(file);}


    /**
     * Read the data stream and return it as a DataFrame. The delimeter
     * needs to be specified
     * @param inputSize the size of the input
     * @param delimeter the delimeter
     * @return
     */
    public DataFrame<String> read(int inputSize, String delimeter) {
        List<String[]> set = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.isEmpty()) {
                set.add(line.split(delimeter));
            }
        }
        return new DataFrame<>(set, inputSize);
    }
    

    /**
     * Read the data stream and return it as a DataFrame.
     * @param inputSize the size of the input
     * @return a DataFrame
     */
    public DataFrame<String> read_csv(int inputSize) {return read(inputSize, ",");}
    

    /**
     * Read the data stream and return it as a DataFrame. The delimeter
     * needs to be specified
     * @param inputSize the size of the input
     * @param delimeter the delimeter
     * @return
     */
    public DataFrame<Double> readDouble(int inputSize, String delimeter) {
        return DataFrame.parseDouble(read(inputSize, delimeter));
    }


    /**
     * Read the data stream and return it as a DataFrame.
     * @param inputSize the size of the input
     * @return a DataFrame
     */
    public DataFrame<Double> readDouble_csv(int inputSize) {return readDouble(inputSize, ",");}
    
}
