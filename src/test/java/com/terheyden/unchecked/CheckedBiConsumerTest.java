package com.terheyden.unchecked;

import java.util.function.BiConsumer;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Extends {@link BiConsumer} to allow throwing checked exceptions.
 *   {@link BiConsumer#accept(Object, Object)}
 *   {@link BiConsumer#andThen(BiConsumer)}
 */
class CheckedBiConsumerTest extends ExceptionTester {

    @Test
    void test() {

        // Accepts checked work.
        CheckedBiConsumer<String, String> consumer = CheckedBiConsumer.of(this::returnChecked);
        // Can be cast to a normal BiConsumer.
        BiConsumer<String, String> biConsumer = consumer;

        biConsumer.accept("aaa", "bbb");
        assertThat(getCounter()).isEqualTo(1);
    }
}
