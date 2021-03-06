package hardlearner.springStudy.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public int calcSum(String filepath) throws IOException {
        LineCallBack<Integer> callBack = new LineCallBack<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.valueOf(line);
            }
        };
        return lineReadTemplate(filepath, callBack, 0);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        LineCallBack<Integer> callBack = new LineCallBack<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value * Integer.valueOf(line);
            }
        };
        return lineReadTemplate(filepath, callBack, 1);
    }

    public String concatenate(String filepath) throws IOException {
        LineCallBack<String> concatenateCallback = new LineCallBack<String>() {
            @Override
            public String doSomethingWithLine(String line, String value) {
                return value + line;
            }
        };
        return lineReadTemplate(filepath, concatenateCallback, "");
    }

    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            return callback.doSomethingWithReader(br);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public <T> T lineReadTemplate(String filepath, LineCallBack<T> callBack, T initVal) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            T res = initVal;
            String line = null;
            while((line = br.readLine()) != null) {
                res = callBack.doSomethingWithLine(line, res);
            }
            return res;
        }catch (IOException e) {
            System.out.println(e.getMessage());
            throw e; // 예외를 던지지 않으면 아래에서 lineReadTemplate()에서 return문을 요구한다
        }
    }
}
