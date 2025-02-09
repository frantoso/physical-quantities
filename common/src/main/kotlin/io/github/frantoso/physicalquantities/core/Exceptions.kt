package io.github.frantoso.physicalquantities.core

/**
 * Exception to throw if the prefix is unknown.
 * @param message the detail message (which is saved for later retrieval by the [Throwable.message] property).
 * @param cause the cause (which is saved for later retrieval by the [Throwable.cause] property).
 *          (A [null] value is permitted, and indicates that the cause is nonexistent or unknown.)
 */
class NoSuchPrefixException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)

/**
 * Exception to throw if the unit is unknown.
 * @param message the detail message (which is saved for later retrieval by the [Throwable.message] property).
 * @param cause the cause (which is saved for later retrieval by the [Throwable.cause] property).
 *          (A [null] value is permitted, and indicates that the cause is nonexistent or unknown.)
 */
class NoSuchUnitException(
    message: String? = null,
    cause: Throwable? = null,
) : Exception(message, cause)
