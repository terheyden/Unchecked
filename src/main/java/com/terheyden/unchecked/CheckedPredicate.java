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
     * The same as {@link Predicate#test(Object)}, but allows throwing checked exceptions.
     */
    @SuppressWarnings("squid:S00112")
    boolean testChecked(T item) throws Throwable;

    /**
     * Unchecked version of {@link #testChecked(Object)}.
     * Use this just like you would use {@link Predicate#test(Object)}.
     * Any exceptions thrown will be rethrown as unchecked automatically.
     *
     * @param item the input argument
     * @return true if the input argument matches the predicate, otherwise false
     */
    @Override
    default boolean test(T item) {
        try {
            return testChecked(item);
        } catch (Throwable t) {
            return CheckedPredicateInternal.throwUnchecked(t);
        }
    }
}

/**
 * Defines a self-contained unchecked throw method.
 */
interface CheckedPredicateInternal {
    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R throwUnchecked(Throwable t) throws T {
        throw (T) t;
    }
}
