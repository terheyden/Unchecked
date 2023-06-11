package com.terheyden.unchecked;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CheckedTriConsumer}.
 */
class CheckedTriConsumerTest extends ExceptionTester {

    @Test
    void test() {
        CheckedTriConsumer<String, String, String> consumer = CheckedTriConsumer.of(this::returnChecked);
        consumer.accept("aaa", "bbb", "ccc");
        assertThat(getCounter()).isEqualTo(1);
    }
}
