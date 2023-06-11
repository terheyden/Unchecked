package com.terheyden.unchecked;

import javax.annotation.Nullable;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.String.format;

/**
 * BaseCheckedTest class.
 */
class BaseCheckedTest {

    protected Path getThrowablePath() throws Throwable {
        return Paths.get("/this/path/does/not/exist");
    }

    protected Path getThrowablePath(@Nullable String path1) throws Throwable {
        if (path1 == null) {
            throw new Throwable("IGNORE: test exception: " + path1);
        }
        return getThrowablePath();
    }

    protected Path getThrowablePath(
        @Nullable String path1,
        @Nullable String path2) throws Throwable {

        if (path1 == null || path2 == null) {
            throw new Throwable(format("IGNORE: test exception: %s, %s", path1, path2));
        }
        return getThrowablePath();
    }

    protected Path getThrowablePath(
        @Nullable String path1,
        @Nullable String path2,
        @Nullable String path3) throws Throwable {

        if (path1 == null || path2 == null || path3 == null) {
            throw new Throwable(format("IGNORE: test exception: %s, %s, %s", path1, path2, path3));
        }
        return getThrowablePath();
    }
}
