package com.terheyden.unchecked;

/**
 * For testing exception stuff.
 */
class ExceptionTester {

    private int counter;

    protected String returnChecked(String... str) throws InterruptedException {
        Thread.sleep(1);
        counter++;
        return str.length == 0 ? "" : str[0];
    }

    protected boolean booleanChecked(String... str) throws InterruptedException {
        Thread.sleep(1);
        counter++;
        return true;
    }

    protected String throwChecked(String... str) throws InterruptedException {
        Thread.sleep(1);
        counter++;
        throw new InterruptedException(str[0]);
    }

    protected int getCounter() {
        return counter;
    }
}
