package com.terheyden.unchecked;

/**
 * ExceptionTester class.
 */
public class ExceptionTester {

    private int counter = 0;

    public String doSomethingChecked(String... strs) throws InterruptedException {
        Thread.sleep(1);
        counter++;
        return String.format("Dear %s, I have been called %d times.", String.join(", ", strs), counter);
    }

    public int getCounter() {
        return counter;
    }
}
