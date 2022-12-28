package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CheckedBiConsumerTest unit tests.
 */
public class CheckedBiConsumerTest {

    @Test
    public void test() {

        ExceptionTester tester = new ExceptionTester();
        CheckedBiConsumer<String, String> consumer = tester::doSomethingChecked;
        consumer.accept("foo", "bar");

        assertEquals(1, tester.getCounter());

    }
}
