package com.terheyden.unchecked;

import java.util.function.BiPredicate;

/**
 * Extends {@link BiPredicate} to allow throwing checked exceptions.
 */
@FunctionalInterface
public interface CheckedBiPredicate<T, U> extends BiPredicate<T, U> {

    /**
     * Static method to create a {@link CheckedBiPredicate} from a lambda.
     */
    static <T, U> CheckedBiPredicate<T, U> of(CheckedBiPredicate<T, U> predicate) {
        return predicate;
    }

    /**
     * Static method to create a {@link CheckedBiPredicate} from a {@link BiPredicate}.
     */
    static <T, U> CheckedBiPredicate<T, U> uncheck(BiPredicate<? super T, ? super U> predicate) {
        return predicate::test;
    }

    /**
     * Test the given items. Equivalent to {@link BiPredicate#test(Object, Object)},
     * but allows throwing checked exceptions.
     */
    boolean testChecked(T expr1, U expr2) throws Throwable;

    @Override
    default boolean test(T expr1, U expr2) {
        try {
            return testChecked(expr1, expr2);
        } catch (Throwable ex) {
            return Unchecked.throwUnchecked(ex);
        }
    }
}
