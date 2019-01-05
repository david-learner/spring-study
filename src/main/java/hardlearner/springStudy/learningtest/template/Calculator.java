package hardlearner.springStudy.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public int calcSum(String filePath) throws IOException {
        BufferedReaderCallback sumCallBack =
                new BufferedReaderCallback() {
                    @Override
                    public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                        Integer sum = 0;
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sum = sum + Integer.valueOf(line);
                        }
                        return sum;
                    }
                };
        return fileReadTemplate(filePath, sumCallBack);
    }

    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            return callback.doSomethingWithReader(br);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public Integer calcMultiply(String filePath) throws IOException {
        BufferedReaderCallback multiplyCallback = new BufferedReaderCallback() {
            @Override
            public Integer doSomethingWithReader(BufferedReader br) throws IOException {
                Integer multiply = 1;
                String line = null;
                while((line = br.readLine()) != null) {
                    multiply = multiply * Integer.valueOf(line);
                }
                return multiply;
            }
        };
        return fileReadTemplate(filePath, multiplyCallback);
    }
}
