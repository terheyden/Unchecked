package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * CheckedBiPredicateTest unit tests.
 */
class CheckedBiPredicateTest extends ExceptionTester {

    @Test
    void test() {

        // Accepts checked work.
        CheckedBiPredicate<String, String> predicate = CheckedBiPredicate.of(this::booleanChecked);
        // Can be cast to a normal BiPredicate.
        CheckedBiPredicate<String, String> biPredicate = predicate;

        predicate.test("aaa", "bbb");
        assertThat(getCounter()).isEqualTo(1);
    }
}
