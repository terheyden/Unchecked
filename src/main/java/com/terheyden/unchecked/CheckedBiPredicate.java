package com.terheyden.unchecked;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

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
     * Test the given items. Equivalent to {@link BiPredicate#test(Object, Object)},
     * but allows throwing checked exceptions.
     */
    @SuppressWarnings("squid:S00112")
    boolean testChecked(T expr1, U expr2) throws Throwable;

    /**
     * Unchecked version of {@link #testChecked(Object, Object)}.
     * Use this just like you would use {@link BiPredicate#test(Object, Object)}.
     * Any exceptions thrown will be rethrown as unchecked automatically.
     *
     * @param expr1 the first input argument
     * @param expr2 the second input argument
     * @return true if the input arguments match the predicate, otherwise false
     */
    @Override
    default boolean test(T expr1, U expr2) {
        try {
            return testChecked(expr1, expr2);
        } catch (Throwable ex) {
            return CheckedBiPredicateInternal.throwUnchecked(ex);
        }
    }

    /**
     * Accepts the first item, and returns a {@link Predicate} that accepts the second item.
     * @param expr1 the first input argument
     * @return a {@link Predicate} that accepts the second item
     */
    default Predicate<U> testPartial(T expr1) {
        return expr2 -> test(expr1, expr2);
    }
}

/**
 * Defines a self-contained unchecked throw method.
 */
interface CheckedBiPredicateInternal {
    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R throwUnchecked(Throwable t) throws T {
        throw (T) t;
    }
}
