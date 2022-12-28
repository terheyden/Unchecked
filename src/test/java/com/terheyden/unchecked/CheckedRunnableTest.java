package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CheckedRunnableTest unit tests.
 */
public class CheckedRunnableTest {

    @Test
    public void test() {

        ExceptionTester tester = new ExceptionTester();
        CheckedRunnable runnable = () -> tester.doSomethingChecked("run");
        runnable.run();

        assertEquals(1, tester.getCounter());
    }
}
