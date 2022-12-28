package com.terheyden.unchecked;

import java.util.function.Function;

/**
 * Extension of {@link Function} that allows throwing checked exceptions.
 */
@FunctionalInterface
public interface CheckedFunction<T, R> extends Function<T, R> {

    /**
     * Static method to create a {@link CheckedFunction} from a lambda.
     */
    static <T, R> CheckedFunction<T, R> of(CheckedFunction<T, R> func) {
        return func;
    }

    /**
     * Static method to create a {@link CheckedFunction} from a {@link Function}.
     */
    static <T, R> CheckedFunction<T, R> uncheck(Function<? super T, ? extends R> func) {
        return func::apply;
    }

    /**
     * Apply the function to the given argument.
     * Equivalent to {@link Function#apply(Object)}, but allows throwing checked exceptions.
     */
    R applyChecked(T item) throws Throwable;

    @Override
    default R apply(T item) {
        try {
            return applyChecked(item);
        } catch (Throwable t) {
            return Unchecked.throwUnchecked(t);
        }
    }
}
