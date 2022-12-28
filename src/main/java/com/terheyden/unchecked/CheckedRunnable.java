package com.terheyden.unchecked;

/**
 * Extends {@link Runnable} and allows for checked exceptions.
 */
@FunctionalInterface
public interface CheckedRunnable extends Runnable {

    /**
     * Static method to create a {@link CheckedRunnable} from a lambda.
     */
    static CheckedRunnable of(CheckedRunnable runnable) {
        return runnable;
    }

    /**
     * Static method to create a {@link CheckedRunnable} from a {@link Runnable}.
     */
    static CheckedRunnable uncheck(Runnable runnable) {
        return runnable::run;
    }

    /**
     * The same as {@link Runnable#run()}, but allows throwing checked exceptions.
     */
    void runChecked() throws Throwable;

    @Override
    default void run() {
        try {
            runChecked();
        } catch (Throwable t) {
            Unchecked.throwUnchecked(t);
        }
    }
}
