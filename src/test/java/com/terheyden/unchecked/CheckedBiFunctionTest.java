package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CheckedBiFunctionTest unit tests.
 */
class CheckedBiFunctionTest extends ExceptionTester {

    @Test
    void test() {

        // Accepts checked work.
        CheckedBiFunction<String, String, String> function = CheckedBiFunction.of(this::returnChecked);
        // Can be cast to a normal BiFunction.
        CheckedBiFunction<String, String, String> biFunction = function;

        biFunction.apply("aaa", "bbb");
        assertThat(getCounter()).isEqualTo(1);
    }
}
