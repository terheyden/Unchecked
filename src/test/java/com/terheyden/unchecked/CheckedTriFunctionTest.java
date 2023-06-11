package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CheckedTriFunctionTest unit tests.
 */
class CheckedTriFunctionTest extends ExceptionTester {

    @Test
    void test() {
        CheckedTriFunction<String, String, String, String> function = CheckedTriFunction.of(this::returnChecked);
        function.apply("aaa", "bbb", "ccc");
        assertThat(getCounter()).isEqualTo(1);
    }
}
