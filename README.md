# Unchecked

_Java functional types — unchecked._

## What is this lib?
### Checked Functional Interfaces
Unchecked provides the following Functional Interfaces:

* `CheckedConsumer`
* `CheckedFunction`
* `CheckedPredicate`
* `CheckedRunnable`
* `CheckedSupplier`
* `CheckedBiConsumer`
* `CheckedBiFunction`
* `CheckedBiPredicate`

Each `Checked` interface does the following:
* Extends the corresponding Java functional interface
  (e.g. `CheckedConsumer` extends `Consumer`)
* Handles code with checked exceptions (see examples below)
* Offers a few extra goodies for doing conversions (see below)

### Handy checked exception utils
You also get a few handy methods from the `Unchecked`
static class:

* `throwUnchecked(Throwable)` — throws a checked exception as unchecked
* `sleep(long)` — unchecked version of `Thread.sleep(long)`
* `runUnchecked(...)` — wrap some code that throws a checked exception
* `getUnchecked(...)` — wrap some code that throws and returns a value

We'll see some examples of why these are so useful below.

## How to use
### Wrapping Java functions

Let's say we want to submit a `Runnable` to our threadpool,
but it throws a checked exception:
```java
pool.execute(() -> {
    Thread.sleep(100); // "Unhandled exception: InterruptedException"
    doStuff();
});
```
The Unchecked lib gives us a few ways to handle this:
```java
pool.execute(CheckedRunnable.of(() -> { // Use: Unchecked.runUnchecked()
    Thread.sleep(100);            // Now this throws unchecked
    doStuff();
}));
```
Or:
```java
pool.execute(() -> {
    runUnchecked(() -> Thread.sleep(100)); // Now this throws unchecked
    doStuff();
}));
```

### Using in your own methods
When writing your own methods, you can use the `Checked` interfaces
to make your lambda expressions cleaner:
```java
// If you create this method:
public String calculateString(Supplier<String> supplier) {
    return supplier.get();
}

// You could end up calling it like this:
String urlStr = calculateString(() -> {
    try {
        URL url = new URL("http://www.terheyden.com"); // throws MalformedURLException
        return url.toString();
    } catch (Exception e) {
        Unchecked.throwUnchecked(e);
    }
});
```
Versus:
```java
// Accept a CheckedSupplier instead:
public String calculateString(CheckedSupplier<String> supplier) {
    return supplier.get();
}

// Life gets much simpler:
String urlStr = calculateString(() -> {
    URL url = new URL("http://www.terheyden.com"); // throws unchecked
    return url.toString();
});
```

### Rethrowing your own checked exceptions
Sometimes you don't want to use a lambda:
```java
try {
    // Do some stuff that throws a checked exception.
} catch (Exception e) {
    Unchecked.throwUnchecked(e);
}
```
This works for code that returns a value too:
```java
try {
    riskyOperation();
    return someValue;
} catch (Exception e) {
    return throwUnchecked(e); // static import version.
}
```

## More information
### What happens to the checked exceptions?
They are simply thrown as unchecked.
They do not get wrapped in a `RuntimeException` or anything else;
they are preserved and thrown exactly as they are.

### What are the requirements?
* Java 1.8+

