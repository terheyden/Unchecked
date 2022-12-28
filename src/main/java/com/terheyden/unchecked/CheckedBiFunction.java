package com.terheyden.unchecked;

import java.util.function.BiFunction;

/**
 * Extends {@link BiFunction} to allow throwing checked exceptions.
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
     * Static method to create a {@link CheckedBiFunction} from a {@link BiFunction}.
     */
    static <T, U, R> CheckedBiFunction<T, U, R> uncheck(BiFunction<? super T, ? super U, ? extends R> func) {
        return func::apply;
    }

    /**
     * Apply the function to the given arguments.
     * Equivalent to {@link BiFunction#apply(Object, Object)}, but allows throwing checked exceptions.
     */
    R applyChecked(T item1, U item2) throws Throwable;

    @Override
    default R apply(T item1, U item2) {
        try {
            return applyChecked(item1, item2);
        } catch (Throwable ex) {
            return Unchecked.throwUnchecked(ex);
        }
    }
}
