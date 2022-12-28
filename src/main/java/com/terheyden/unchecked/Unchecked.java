package com.terheyden.unchecked;

/**
 * Methods for dealing with checked exceptions.
 */
public final class Unchecked {

    private Unchecked() {
        // Private since this class shouldn't be instantiated.
    }

    /**
     * Get a value from a statement that throws a checked exception.
     */
    public static <T> T getUnchecked(CheckedSupplier<T> supplier) {
        return supplier.get();
    }

    public static void runUnchecked(CheckedRunnable runnable) {
        runnable.run();
    }

    /**
     * Throw any exception unchecked.
     */
    @SuppressWarnings("unchecked")
    public static <E extends Throwable, R> R throwUnchecked(Throwable throwable) throws E {
        throw (E) throwable;
    }

    /**
     * {@link Thread#sleep(long)} unchecked.
     */
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throwUnchecked(e);
        }
    }
}
