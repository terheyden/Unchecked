package com.terheyden.unchecked;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Functional interface that accepts three arguments and returns a boolean.
 */
@FunctionalInterface
public interface CheckedTriPredicate<T, U, V> {

    /**
     * Static method to create a {@link CheckedTriPredicate} from a lambda.
     */
    static <T, U, V> CheckedTriPredicate<T, U, V> of(CheckedTriPredicate<T, U, V> predicate) {
        return predicate;
    }

    /**
     * Test the given items.
     */
    @SuppressWarnings("squid:S00112")
    boolean testChecked(T expr1, U expr2, V expr3) throws Throwable;

    /**
     * Unchecked version of {@link #testChecked(Object, Object, Object)}.
     * Any exceptions thrown will be rethrown as unchecked automatically.
     *
     * @param expr1 the first input argument
     * @param expr2 the second input argument
     * @param expr3 the third input argument
     * @return true if the input arguments match the predicate, otherwise false
     */
    default boolean test(T expr1, U expr2, V expr3) {
        try {
            return testChecked(expr1, expr2, expr3);
        } catch (Throwable ex) {
            return CheckedTriPredicateInternal.throwUnchecked(ex);
        }
    }

    /**
     * Accepts the first item, and returns a {@link BiPredicate} that accepts the second and third items.
     * @param expr1 the first input argument
     * @return a {@link BiPredicate} that accepts the second and third items
     */
    default BiPredicate<U, V> testPartial(T expr1) {
        return (expr2, expr3) -> test(expr1, expr2, expr3);
    }

    /**
     * Accepts the first and second items, and returns a {@link Predicate} that accepts the third item.
     * @param expr1 the first input argument
     * @param expr2 the second input argument
     * @return a {@link Predicate} that accepts the third item
     */
    default Predicate<V> testPartial(T expr1, U expr2) {
        return expr3 -> test(expr1, expr2, expr3);
    }
}

/**
 * Defines a self-contained unchecked throw method.
 */
interface CheckedTriPredicateInternal {
    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R throwUnchecked(Throwable t) throws T {
        throw (T) t;
    }
}
