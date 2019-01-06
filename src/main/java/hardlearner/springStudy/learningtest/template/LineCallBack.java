package hardlearner.springStudy.learningtest.template;

public interface LineCallBack<T> {
    T doSomethingWithLine(String line, T value);
}
