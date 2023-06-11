package com.terheyden.unchecked;

import java.net.URL;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import static com.terheyden.unchecked.CheckedSupplier.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * CheckedSupplierTest unit tests.
 */
class CheckedSupplierTest extends ExceptionTester {

    @Test
    void test() {

        // Accepts checked work.
        CheckedSupplier<String> supplier = of(() -> returnChecked("hi there"));
        // Can be cast to a normal Supplier.
        Supplier<String> normal = supplier;

        supplier.get();
        assertThat(getCounter()).isEqualTo(1);
    }
}
