package com.terheyden.unchecked;

import java.net.URL;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CheckedSupplierTest unit tests.
 */
public class CheckedSupplierTest {

    @Test
    public void test() {

        ExceptionTester tester = new ExceptionTester();
        CheckedSupplier<String> supplier = () -> tester.doSomethingChecked("foo");
        supplier.get();

        assertEquals(1, tester.getCounter());

        String urlStr = calculateString(() -> {
            URL url = CheckedSupplier.of(() -> new URL("http://www.terheyden.com")).get(); // throws MalformedURLException
            return url.toString();
        });
    }

    public String calculateString(Supplier<String> supplier) {
        return supplier.get();
    }
}
