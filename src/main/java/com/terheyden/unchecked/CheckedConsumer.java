package com.terheyden.unchecked;

import java.util.function.Consumer;

/**
 * Extension of {@link Consumer} that allows throwing checked exceptions.
 */
@FunctionalInterface
public interface CheckedConsumer<T> extends Consumer<T> {

    /**
     * Static method to create a {@link CheckedConsumer} from a lambda.
     */
    static <T> CheckedConsumer<T> of(CheckedConsumer<T> consumer) {
        return consumer;
    }

    /**
     * Static method to create a {@link CheckedConsumer} from a {@link Consumer}.
     */
    static <T> CheckedConsumer<T> uncheck(Consumer<? super T> consumer) {
        return consumer::accept;
    }

    /**
     * Accept the given item. Equivalent to {@link Consumer#accept(Object)},
     * but allows throwing checked exceptions.
     */
    void acceptChecked(T item) throws Throwable;

    @Override
    default void accept(T item) {
        try {
            acceptChecked(item);
        } catch (Throwable t) {
            Unchecked.throwUnchecked(t);
        }
    }
}
