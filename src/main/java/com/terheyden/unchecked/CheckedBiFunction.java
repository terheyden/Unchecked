package com.terheyden.unchecked;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A functional interface that extends {@link BiFunction}.
 * Accepts checked exceptions, and recasts them as unchecked.
 * Use this just like you would use {@link BiFunction}.
 * Example:
 * <pre>{@code
 * someFunc(CheckedBiFunction.of((item1, item2) -> this::doSomething));
 * }</pre>
 */
@FunctionalInterface
public interface CheckedBiFunction<T, U, R> extends BiFunction<T, U, R> {

    /**
     * Static method to create a {@link CheckedBiFunction} from a lambda.
     */
    static <T, U, R> CheckedBiFunction<T, U, R> of(CheckedBiFunction<T, U, R> func) {
        return func;
    }

    /**
     * Apply the function to the given arguments.
     * Equivalent to {@link BiFunction#apply(Object, Object)}, but allows throwing checked exceptions.
     */
    @SuppressWarnings("squid:S00112")
    R applyChecked(T item1, U item2) throws Throwable;

    /**
     * Unchecked version of {@link BiFunction#apply(Object, Object)}.
     * Use this just as you would use {@link BiFunction#apply(Object, Object)}.
     * Any exceptions thrown will be recast as unchecked; note that the exception is
     * exactly the same, no wrapping or modification to the exception is done.
     *
     * @param item1 the first function argument
     * @param item2 the second function argument
     * @return the function result
     */
    @Override
    default R apply(T item1, U item2) {
        try {
            return applyChecked(item1, item2);
        } catch (Throwable ex) {
            return CheckedBiFunctionInternal.throwUnchecked(ex);
        }
    }

    /**
     * Partially apply this function to the given arguments.
     * @param item1 the first function argument
     * @return a function that takes the second argument and returns the function result
     */
    default Function<U, R> applyPartial(T item1) {
        return item2 -> apply(item1, item2);
    }

    /**
     * Returns a curried version of this function.
     */
    default Function<T, Function<U, R>> curry() {
        return item1 -> item2 -> apply(item1, item2);
    }
}

/**
 * Defines a self-contained unchecked throw method.
 */
interface CheckedBiFunctionInternal {
    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R throwUnchecked(Throwable t) throws T {
        throw (T) t;
    }
}
