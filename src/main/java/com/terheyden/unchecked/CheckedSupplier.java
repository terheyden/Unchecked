package com.terheyden.unchecked;

import java.util.function.Supplier;

/**
 * Extension of {@link Supplier} that allows throwing checked exceptions.
 * Accepts checked exceptions, and recasts them as unchecked.
 * Use this just like you would use {@link Supplier#get()}.
 * Example:
 * <pre>{@code
 * someFunc(CheckedSupplier.of(() -> this::doSomething));
 * }</pre>
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
     * The same as {@link Supplier#get()}, but allows throwing checked exceptions.
     */
    T getChecked() throws Throwable;

    /**
     * Unchecked version of {@link #getChecked()}.
     * Use this just like you would use {@link Supplier#get()}.
     * Any exceptions thrown will be recast as unchecked; note that the exception is
     * exactly the same, no wrapping or modification to the exception is done.
     *
     * @return the result of {@link #getChecked()}
     */
    @Override
    default T get() {
        try {
            return getChecked();
        } catch (Throwable t) {
            return CheckedSupplierInternal.throwUnchecked(t);
        }
    }
}

/**
 * Defines a self-contained unchecked throw method.
 */
interface CheckedSupplierInternal {
    @SuppressWarnings("unchecked")
    static <T extends Throwable, R> R throwUnchecked(Throwable t) throws T {
        throw (T) t;
    }
}
