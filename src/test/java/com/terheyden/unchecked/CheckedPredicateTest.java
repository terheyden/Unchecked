package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CheckedPredicateTest unit tests.
 */
public class CheckedPredicateTest {

    @Test
    public void test() {

        CheckedPredicate<String> predicate = str -> {
            Thread.sleep(1);
            return !str.isEmpty();
        };

        assertTrue(predicate.test("foo"));
    }
}
