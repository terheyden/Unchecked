package com.terheyden.unchecked;

import java.io.Serializable;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A functional interface that accepts three items and returns nothing.
 * Accepts checked exceptions, and recasts them as unchecked.
 * Example:
 * <pre>{@code
 * someFunc(CheckedTriConsumer.of((item1, item2, item3) -> this::doSomething));
 * }</pre>
 */
@FunctionalInterface
public interface CheckedTriConsumer<T, U, V> extends Serializable {

    long serialVersionUID = 1L;

    /**
     * Static method to create a {@link CheckedTriConsumer} from an expression.
     */
    static <T, U, V> CheckedTriConsumer<T, U, V> of(CheckedTriConsumer<T, U, V> consumer) {
        return consumer;
    }

    /**
     * Accept the given items. Equivalent to {@link TriConsumer#accept(Object, Object)},
     * but accepts expressions that throw checked exceptions.
     */
    @SuppressWarnings("squid:S00112")
    void acceptChecked(T item1, U item2, V item3) throws Throwable;

    /**
     * Unchecked version of {@link #acceptChecked(Object, Object)}.
     * Any exceptions thrown will be recast as unchecked; note that the exception is
     * exactly the same, no wrapping or modification to the exception is done.
     *
     * @param item1 the first input argument
     * @param item2 the second input argument
     * @param item3 the third input argument
     */
    default void accept(T item1, U item2, V item3) {
        try {
            acceptChecked(item1, item2, item3);
        } catch (Throwable ex) {
            CheckedTriConsumerInternal.throwUnchecked(ex);
        }
    }

    /**
     * Accepts the first item, and returns a {@link BiConsumer} that accepts the second and third items.
     * @param item1 the first input argument
     * @return a {@link BiConsumer} that accepts the second and third items
     */
    default BiConsumer<U, V> acceptPartial(T item1) {
        return (item2, item3) -> accept(item1, item2, item3);
    }

    /**
     * Accepts the first and second items, and returns a {@link Consumer} that accepts the third item.
     * @param item1 the first input argument
     * @param item2 the second input argument
     * @return a {@link Consumer} that accepts the third item
     */
    default Consumer<V> acceptPartial(T item1, U item2) {
        return item3 -> accept(item1, item2, item3);
    }
}

/**
 * Defines a self-contained unchecked throw method.
 */
interface CheckedTriConsumerInternal {
    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R throwUnchecked(Throwable t) throws T {
        throw (T) t;
    }
}
