package logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class FileToMemoryReader extends Thread {
    List<Integer> list;
    String pathToFile;

    public FileToMemoryReader(List<Integer> list, String pathToFile) {
        this.list = list;
        this.pathToFile = pathToFile;
    }

    @Override
    public void run() {
        boolean inputParams = pathToFile.endsWith("param-logs.txt");
        File fileToRead = new File(pathToFile);
        try {
            Scanner reader = new Scanner(fileToRead);
            if (inputParams) {
                reader.nextLine(); // num of samples
            }
            while (reader.hasNextLine()) {
                list.add(Integer.parseInt(reader.nextLine()));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
