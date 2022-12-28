package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CheckedBiFunctionTest unit tests.
 */
public class CheckedBiFunctionTest {

    @Test
    public void test() {

        ExceptionTester tester = new ExceptionTester();
        CheckedBiFunction<String, String, String> function = tester::doSomethingChecked;
        function.apply("foo", "bar");

        assertEquals(1, tester.getCounter());
    }
}
