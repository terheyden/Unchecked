package com.terheyden.unchecked;

import java.net.URL;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CheckedUtilsTest unit tests.
 */
class CheckedUtilsTest extends ExceptionTester {

    @Test
    void test() {
        // Stupid new URL() throws a checked exception.
        assertThat(urlDownloader(CheckedUtils.get(() -> new URL("http://www.google.com"))))
            .isEqualTo("http://www.google.com");
    }

    private static String urlDownloader(URL url) {
        return url.toString();
    }
}
