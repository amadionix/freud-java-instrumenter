package logging;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;
import java.util.*;

public class Logger {

    public static void main(String[] args) {
        Samples samples = new Samples();
        FileToMemoryReader paramsReader, execTimesReader;
        List<Integer> paramsList = new ArrayList<>();
        List<Integer> execTimesList = new ArrayList<>();
        String pathToParamsLog = System.getProperty("user.dir") +
                "/param-logs.txt";
        String pathToExecTimesLog = System.getProperty("user.dir") +
                "/time-logs.txt";

        paramsReader = new FileToMemoryReader(paramsList, pathToParamsLog);
        execTimesReader = new FileToMemoryReader(execTimesList, pathToExecTimesLog);
        paramsReader.start();
        execTimesReader.start();
        try {
            paramsReader.join();
            execTimesReader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Read from file to memory completed successfully!");

        for (int i = 0; i < paramsList.size(); i++) {
            samples.add(new Sample(paramsList.get(i), execTimesList.get(i)));
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
        try {
            String jsonLog = objectMapper.writeValueAsString(samples);
            System.out.println(jsonLog);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
