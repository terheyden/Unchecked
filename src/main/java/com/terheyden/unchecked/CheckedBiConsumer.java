package com.terheyden.unchecked;

import java.util.function.BiConsumer;

/**
 * Extends {@link BiConsumer} to allow throwing checked exceptions.
 */
@FunctionalInterface
public interface CheckedBiConsumer<T, U> extends BiConsumer<T, U> {

    /**
     * Static method to create a {@link CheckedBiConsumer} from a lambda.
     */
    static <T, U> CheckedBiConsumer<T, U> of(CheckedBiConsumer<T, U> consumer) {
        return consumer;
    }

    /**
     * Static method to create a {@link CheckedBiConsumer} from a {@link BiConsumer}.
     */
    static <T, U> CheckedBiConsumer<T, U> uncheck(BiConsumer<? super T, ? super U> consumer) {
        return consumer::accept;
    }

    /**
     * Accept the given items. Equivalent to {@link BiConsumer#accept(Object, Object)},
     * but allows throwing checked exceptions.
     */
    void acceptChecked(T item1, U item2) throws Throwable;

    @Override
    default void accept(T item1, U item2) {
        try {
            acceptChecked(item1, item2);
        } catch (Throwable ex) {
            Unchecked.throwUnchecked(ex);
        }
    }
}
