# Physical Quantities

## Introduction

Test project to create a kotlin lib to handle physical quantities in code in a type safe manner.

## Examples

```kotlin
val power = 23.V.times(42.A)

assertThat(power).isEqualTo(966.W)
```

Also long and short-form prefixes are supported; please note the `_` in `_m` to distinguish milli from meters.
Millimeters are `_m.m`

```kotlin
val power = 23.milli.V.times(42._m.A)
```

