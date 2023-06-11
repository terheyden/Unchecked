package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CheckedPredicateTest unit tests.
 */
class CheckedPredicateTest extends ExceptionTester {

    @Test
    void test() {

        // Accepts checked work.
        CheckedPredicate<String> predicate = CheckedPredicate.of(this::booleanChecked);
        // Can be cast to a normal Predicate.
        CheckedPredicate<String> normal = predicate;

        predicate.test("aaa");
        assertThat(getCounter()).isEqualTo(1);
    }
}
