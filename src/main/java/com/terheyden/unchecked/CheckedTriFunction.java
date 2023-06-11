package com.terheyden.unchecked;

import java.io.Serializable;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A functional interface that accepts three arguments and returns a value.
 * Accepts checked exceptions, and recasts them as unchecked.
 * Example:
 * <pre>{@code
 * someFunc(CheckedTriFunction.of((item1, item2, item3) -> this::doSomething));
 * }</pre>
 */
@FunctionalInterface
public interface CheckedTriFunction<T, U, V, R> extends Serializable {

    long serialVersionUID = 1L;

    /**
     * Static method to create a {@link CheckedTriFunction} from a lambda.
     */
    static <T, U, V, R> CheckedTriFunction<T, U, V, R> of(CheckedTriFunction<T, U, V, R> func) {
        return func;
    }

    /**
     * Apply the function to the given arguments.
     */
    @SuppressWarnings("squid:S00112")
    R applyChecked(T item1, U item2, V item3) throws Throwable;

    /**
     * Unchecked version of {@link TriFunction#apply(Object, Object)}.
     * Any exceptions thrown will be recast as unchecked; note that the exception is
     * exactly the same, no wrapping or modification to the exception is done.
     *
     * @param item1 the first function argument
     * @param item2 the second function argument
     * @param item3 the third function argument
     * @return the function result
     */
    default R apply(T item1, U item2, V item3) {
        try {
            return applyChecked(item1, item2, item3);
        } catch (Throwable ex) {
            return CheckedTriFunctionInternal.throwUnchecked(ex);
        }
    }

    /**
     * Partially apply this function to the given arguments.
     * @param item1 the first function argument
     * @param item2 the second function argument
     * @return a function that takes the third argument and returns the function result
     */
    default Function<V, R> applyPartial(T item1, U item2) {
        return item3 -> apply(item1, item2, item3);
    }

    /**
     * Partially apply this function to the given arguments.
     * @param item1 the first function argument
     * @return a function that takes the second and third arguments and returns the function result
     */
    default BiFunction<U, V, R> applyPartial(T item1) {
        return (item2, item3) -> apply(item1, item2, item3);
    }

    /**
     * Return a curried version of this function.
     */
    default Function<T, Function<U, Function<V, R>>> curry() {
        return item1 -> item2 -> item3 -> apply(item1, item2, item3);
    }
}

/**
 * Defines a self-contained unchecked throw method.
 */
interface CheckedTriFunctionInternal {
    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R throwUnchecked(Throwable t) throws T {
        throw (T) t;
    }
}
