package hardlearner.springStudy.learningtest.template;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalcSumTest {

//    @Rule
//    TemporaryFolder tmpFolder = new TemporaryFolder();
//
    @Test
    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        String path = getClass().getResource("/numbers.txt").getPath();
        int sum = calculator.calcSum(path);
        assertThat(sum, is(10));
    }

    @Test
    public void resourceIsNull() {
        try (BufferedReader br = null) {
            System.out.println("test");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
