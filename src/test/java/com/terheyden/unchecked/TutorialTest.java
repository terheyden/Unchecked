package com.terheyden.unchecked;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * I use this code in the README.md.
 */
class TutorialTest {

    void tutorial() {

//        Stream.of(Paths.get("someFile.txt"))
//            .map(Path::toAbsolutePath)
//            .map(file -> Files.readAllLines(file))
//            .forEach(lines -> lines.forEach(System.out::println));

        Stream.of(Paths.get("someFile.txt"))
            .map(Path::toAbsolutePath)
            .map(file -> {
                try {
                    return Files.readAllLines(file);
                } catch (Exception e) {
                    throw new IllegalStateException("Failed to read file: " + file, e);
                }
            })
            .forEach(lines -> lines.forEach(System.out::println));

        Stream.of(Paths.get("someFile.txt"))
            .map(Path::toAbsolutePath)
            .map(CheckedFunction.of(Files::readAllLines))
            .forEach(lines -> lines.forEach(System.out::println));

        Stream.of(Paths.get("someFile.txt"))
            .map(Path::toAbsolutePath)
            .map(file -> CheckedUtils.get(() -> Files.readAllLines(file)))
            .forEach(lines -> lines.forEach(System.out::println));

    }
}
