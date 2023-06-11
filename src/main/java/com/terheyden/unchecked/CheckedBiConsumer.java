package com.terheyden.unchecked;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A functional interface that extends {@link BiConsumer}.
 * Accepts checked exceptions, and recasts them as unchecked.
 * Use this just like you would use {@link BiConsumer}.
 * Example:
 * <pre>{@code
 * someFunc(CheckedBiConsumer.of((item1, item2) -> this::doSomething));
 * }</pre>
 */
@FunctionalInterface
public interface CheckedBiConsumer<T, U> extends BiConsumer<T, U> {

    /**
     * Static method to create a {@link CheckedBiConsumer} from an expression.
     */
    static <T, U> CheckedBiConsumer<T, U> of(CheckedBiConsumer<T, U> consumer) {
        return consumer;
    }

    /**
     * Accept the given items. Equivalent to {@link BiConsumer#accept(Object, Object)},
     * but accepts expressions that throw checked exceptions.
     */
    @SuppressWarnings("squid:S00112")
    void acceptChecked(T item1, U item2) throws Throwable;

    /**
     * Unchecked version of {@link #acceptChecked(Object, Object)}.
     * Use this just as you would use {@link BiConsumer#accept(Object, Object)}.
     * Any exceptions thrown will be recast as unchecked; note that the exception is
     * exactly the same, no wrapping or modification to the exception is done.
     *
     * @param item1 the first input argument
     * @param item2 the second input argument
     */
    @Override
    default void accept(T item1, U item2) {
        try {
            acceptChecked(item1, item2);
        } catch (Throwable ex) {
            CheckedBiConsumerInternal.throwUnchecked(ex);
        }
    }

    /**
     * Accepts the first item, and returns a {@link Consumer} that accepts the second item.
     */
    default Consumer<U> acceptPartial(T item1) {
        return item2 -> accept(item1, item2);
    }
}

/**
 * Defines a self-contained unchecked throw method.
 */
interface CheckedBiConsumerInternal {
    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R throwUnchecked(Throwable t) throws T {
        throw (T) t;
    }
}
