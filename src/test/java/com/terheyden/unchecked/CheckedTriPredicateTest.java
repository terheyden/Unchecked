package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CheckedTriPredicateTest unit tests.
 */
class CheckedTriPredicateTest extends ExceptionTester {

    @Test
    void test() {
        CheckedTriPredicate<String, String, String> predicate = CheckedTriPredicate.of(this::booleanChecked);
        predicate.test("aaa", "bbb", "ccc");
        assertThat(getCounter()).isEqualTo(1);
    }
}
