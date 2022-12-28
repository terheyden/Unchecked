package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CheckedBiPredicateTest unit tests.
 */
public class CheckedBiPredicateTest {

    @Test
    public void test() {

        CheckedBiPredicate<String, String> predicate = (str1, str2) -> {
            Thread.sleep(1);
            return !str1.isEmpty() && !str2.isEmpty();
        };

        assertTrue(predicate.test("foo", "bar"));
    }
}
