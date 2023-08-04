package ch.magdenbt.a1ccaclient.model.accounts.entities

import ch.magdenbt.a1ccaclient.model.EmptyFieldException
import ch.magdenbt.a1ccaclient.model.Field

data class Account(
    val caURL: String,
    val username: String,
    val password: String,
    ) {
    fun validate() {
        if (caURL.isBlank()) throw EmptyFieldException(Field.CA_URL)
        if (username.isBlank()) throw EmptyFieldException(Field.Username)
        if (password.isBlank()) throw EmptyFieldException(Field.Password)
    }
}
