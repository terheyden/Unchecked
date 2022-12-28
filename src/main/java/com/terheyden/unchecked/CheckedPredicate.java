package com.terheyden.unchecked;

import java.util.function.Predicate;

/**
 * Extension of {@link Predicate} that allows throwing checked exceptions.
 */
@FunctionalInterface
public interface CheckedPredicate<T> extends Predicate<T> {

    /**
     * Static method to create a {@link CheckedPredicate} from a lambda.
     */
    static <T> CheckedPredicate<T> of(CheckedPredicate<T> predicate) {
        return predicate;
    }

    /**
     * Static method to create a {@link CheckedPredicate} from a {@link Predicate}.
     */
    static <T> CheckedPredicate<T> uncheck(Predicate<? super T> predicate) {
        return predicate::test;
    }

    /**
     * The same as {@link Predicate#test(Object)}, but allows throwing checked exceptions.
     */
    boolean testChecked(T item) throws Throwable;

    @Override
    default boolean test(T item) {
        try {
            return testChecked(item);
        } catch (Throwable t) {
            return Unchecked.throwUnchecked(t);
        }
    }
}
