package com.terheyden.unchecked;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CheckedBiFunctionTest unit tests.
 */
public class CheckedFunctionTest {

    @Test
    public void test() {

        ExceptionTester tester = new ExceptionTester();
        CheckedFunction<String, String> function = tester::doSomethingChecked;
        function.apply("foo");

        assertEquals(1, tester.getCounter());
    }

    @Test
    public void testAndThen() {

        CheckedFunction<String, Integer> lenFun = str -> {
            Thread.sleep(1);
            return str.length();
        };

        Function<String, Integer> lenx2 = lenFun.andThen(CheckedFunction.of(len -> {
            Thread.sleep(1);
            return len * 2;
        }));

        assertEquals(6, lenx2.apply("foo").intValue());
    }
}
