package com.terheyden.unchecked;

import java.util.function.Consumer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CheckedConsumerTest unit tests.
 */
class CheckedConsumerTest extends ExceptionTester {

    @Test
    void test() {

        // Accepts checked work.
        CheckedConsumer<String> consumer = CheckedConsumer.of(this::returnChecked);
        // Can be cast to a normal Consumer.
        Consumer<String> consumer2 = consumer;

        consumer.accept("aaa");
        assertThat(getCounter()).isEqualTo(1);
    }
}
