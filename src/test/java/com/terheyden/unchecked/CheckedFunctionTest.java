package com.terheyden.unchecked;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CheckedBiFunctionTest unit tests.
 */
class CheckedFunctionTest extends ExceptionTester {

    @Test
    void test() {

        // Accepts checked work.
        CheckedFunction<String, String> function = CheckedFunction.of(this::returnChecked);
        // Can be cast to a normal Function.
        Function<String, String> normal = function;

        function.apply("foo");
        assertThat(getCounter()).isEqualTo(1);
    }
}
