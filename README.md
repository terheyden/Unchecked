# Unchecked library for Java

_Java functional interfaces that accept checked exceptions._

## What is this lib?

We've all been there.
This is the code you wanted to write...

```java
// Read in a text file, print out each line.
Stream.of(Paths.get("someFile.txt"))
    .map(Path::toAbsolutePath)
    .map(file -> Files.readAllLines(file)) // throws IOException!
    .forEach(lines -> lines.forEach(System.out::println));
```

But because of that checked exception, you ended up with:

```java
// Read in a text file, print out each line.
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
```

Use this lib! Be happy again:

```java
// Read in a text file, print out each line.
Stream.of(Paths.get("someFile.txt"))
    .map(Path::toAbsolutePath)
    .map(CheckedFunction.of(Files::readAllLines)) // Unchecked, yay!
    .forEach(lines -> lines.forEach(System.out::println));
```

There is also a `CheckedUtils.get()` method that provides a shortcut
(shown here as a static import for maximum brevity):

```java
// Read in a text file, print out each line.
Stream.of(Paths.get("someFile.txt"))
    .map(Path::toAbsolutePath)
    .map(file -> get(() -> Files.readAllLines(file))) // Unchecked again!
    .forEach(lines -> lines.forEach(System.out::println));
```

## Checked Functional Interfaces
Unchecked provides the following Functional Interfaces:

* `CheckedConsumer`, `CheckedBiConsumer`, `CheckedTriConsumer`
* `CheckedFunction`, `CheckedBiFunction`, `CheckedTriFunction`
* `CheckedPredicate`, `CheckedBiPredicate`, `CheckedTriPredicate`
* `CheckedRunnable`
* `CheckedSupplier`

### Notes

- Each `Checked-` and `CheckedBi-` interface extends the corresponding Java functional interface
  (e.g. `CheckedConsumer` extends `Consumer`)
  - This means they can be used anywhere a Java functional interface is expected
  - (There are no `Tri-` interfaces in Java so the `CheckedTri-` interfaces only extend `Serializable`.)
- Support for partial application (currying) of `Bi-` and `Tri-` interfaces
- Every interface is written independently, with no external dependencies
  - If adding more dependencies to your project is difficult, please feel free to copy/paste what you need

## What happens to the checked exceptions?
They are simply recast as unchecked.
They are not wrapped or modified in any way; you're getting the original exception.

## Handy checked exception utils
You also get a few handy methods from the `CheckedUtils`
static class:

* `throwUnchecked(Throwable)` — throws a checked exception as unchecked
* `sleep(long)` — unchecked version of `Thread.sleep(long)`
* `run(...)` — wrap some code that throws a checked exception
* `get(...)` — wrap some code that throws and returns a value

## Banning Checked Exceptions

Use `CheckedUtils.throwUnchecked()` to recast checked exceptions as unchecked:

```java
try {
    riskyOperation();
} catch (Exception e) {
    Unchecked.throwUnchecked(e);
}
```

This works for code that returns a value too:

```java
try {
    return riskyCalculation();
} catch (Exception e) {
    return throwUnchecked(e); // Method return requirement is satisfied.
}
```

## What are the requirements?
* Java 1.8+

