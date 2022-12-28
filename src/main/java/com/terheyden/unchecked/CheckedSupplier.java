package com.terheyden.unchecked;

import java.util.function.Supplier;

/**
 * Extension of {@link Supplier} that allows throwing checked exceptions.
 */
@FunctionalInterface
public interface CheckedSupplier<T> extends Supplier<T> {

    /**
     * Static method to create a {@link CheckedSupplier} from a lambda.
     */
    static <T> CheckedSupplier<T> of(CheckedSupplier<T> supplier) {
        return supplier;
    }

    /**
     * Static method to create a {@link CheckedSupplier} from a {@link Supplier}.
     */
    static <T> CheckedSupplier<T> uncheck(Supplier<? extends T> supplier) {
        return supplier::get;
    }

    /**
     * The same as {@link Supplier#get()}, but allows throwing checked exceptions.
     */
    T getChecked() throws Throwable;

    @Override
    default T get() {
        try {
            return getChecked();
        } catch (Throwable t) {
            return Unchecked.throwUnchecked(t);
        }
    }
}
