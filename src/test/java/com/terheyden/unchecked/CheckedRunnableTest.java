package com.terheyden.unchecked;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CheckedRunnableTest unit tests.
 */
class CheckedRunnableTest extends ExceptionTester {

    @Test
    void test() {

        // Accepts checked work.
        CheckedRunnable runnable = CheckedRunnable.of(this::returnChecked);
        // Can be cast to a normal Runnable.
        Runnable normal = runnable;

        runnable.run();
        assertThat(getCounter()).isEqualTo(1);
    }
}
