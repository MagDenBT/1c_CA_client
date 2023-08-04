package ch.magdenbt.a1ccaclient.model

open class AppException : RuntimeException()

class EmptyFieldException(
    val field: Field
) : AppException()

class AccountNotFound : AppException()

class AuthException : AppException()

class NoCachedData : AppException()
