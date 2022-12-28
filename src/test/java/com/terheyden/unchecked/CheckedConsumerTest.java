package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CheckedConsumerTest unit tests.
 */
public class CheckedConsumerTest {

    @Test
    public void test() {

        ExceptionTester tester = new ExceptionTester();
        CheckedConsumer<String> consumer = tester::doSomethingChecked;
        consumer.accept("foo");

        assertEquals(1, tester.getCounter());
    }
}
