package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static com.terheyden.unchecked.Unchecked.getUnchecked;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * UncheckedTest unit tests.
 */
public class UncheckedTest {

    @Test
    public void test() {

        ExceptionTester tester = new ExceptionTester();
        getUnchecked(() -> tester.doSomethingChecked("run"));
        assertEquals(1, tester.getCounter());

        String str = getUnchecked(() -> tester.doSomethingChecked("get"));
        assertNotNull(str);
        assertEquals(2, tester.getCounter());
    }
}
