package com.terheyden.unchecked;

/**
 * CheckedUtils class.
 */
public final class CheckedUtils {

    private CheckedUtils() {
        // Private since this class shouldn't be instantiated.
    }

    /**
     * Throw any exception unchecked.
     * Does not wrap or modify the exception in any way.
     *
     * @param ex The exception to throw.
     * @return Has an adaptable return type, so you can use it where a value is expected.
     */
    @SuppressWarnings("unchecked")
    public static <E extends Throwable, R> R throwUnchecked(Throwable ex) throws E {
        throw (E) ex;
    }

    /**
     * An unchecked version of {@link Thread#sleep(long)}.
     * @param millis The number of milliseconds to sleep.
     */
    @SuppressWarnings("squid:S2142")
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throwUnchecked(ex);
        }
    }

    /**
     * Run any expression that throws a checked exception as unchecked.
     * This is shorthand for:
     * <pre>{@code
     * CheckedRunnable.of(() -> myCheckedFunc()).run();
     * }</pre>
     * @param runnable The expression to run.
     * @see CheckedRunnable
     */
    public static void run(CheckedRunnable runnable) {
        runnable.run();
    }

    /**
     * Get the value of any expression that throws a checked exception as unchecked.
     * This is shorthand for:
     * <pre>{@code
     * CheckedSupplier.of(() -> myCheckedFunc()).get();
     * }</pre>
     * @param supplier The expression to run.
     * @return The value of the expression.
     * @see CheckedSupplier
     */
    public static <T> T get(CheckedSupplier<T> supplier) {
        return supplier.get();
    }
}
