# Physical Quantities

## Introduction

This is a Kotlin library for processing physical quantities in code in a type-safe manner.

Simple numbers can be converted into quantities. This gives the numbers a meaning and they cannot be
freely combined with other numbers or quantities. Only supported calculations are possible.

The checks are performed during compilation.

## How to use

### Gradle

The library physical quantities is distributed via MavenCentral.

There are two versions available. The difference is the data type used to store the values. One
version supports Double and the other BigDecimal.

**build.gradle.kts (Double)**

```kotlin
repositories {
  mavenCentral()
}

dependencies {
  implementation("io.github.frantoso:physical-quantities:<version>")
}
```

**build.gradle.kts (BigDecimal)**

```kotlin
repositories {
  mavenCentral()
}

dependencies {
  implementation("io.github.frantoso:physical-quantities-bd:<version>")
}
```

### Create a quantity

Quantities can be created by a member function of a special quantity or via a unit.

e.g.

```kotlin
val current = Current.fromAmpere(12)
```

Is the same as

```kotlin
val current = 12.A
```

To handle small and big values modifiers are provided as prefixes. There is a long and a short 
form available.

Long variant:

```kotlin
val current = 12.milli.A
```

or short:

```kotlin
val current = 12._m.A
```

Short-form prefixes have a leading underscore to be able to differentiate a prefix from a unit.

### Examples

Multiplying a voltage with a current will result in power.

```kotlin
val power = 23.V * 42.A

assertThat(power).isEqualTo(966.W)
```

Some quantities support multiple units, e.g. pressure:

```kotlin
val pressure = 23._h.Pa
val anotherPressure = 42._m.bar

assertThat(pressure).isEqualTo(2_300.Pa)
assertThat(anotherPressure).isEqualTo(42._h.Pa)
```

## Contributing

Currently the library contains a few quantities only (what I need most). Help in extending
the lib is welcome.

If you think something is missing (I'm sure there's a lot), prepare a pull request.

