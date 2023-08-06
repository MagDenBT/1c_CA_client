package ch.magdenbt.a1ccaclient.model.settings

import ch.magdenbt.a1ccaclient.model.accounts.entities.Account
import kotlinx.coroutines.flow.Flow
import ch.magdenbt.a1ccaclient.model.AccountNotFound

interface AppSettings {

    /**
     * @throws AccountNotFound
     */
    suspend fun getAccount(): Account

    suspend fun saveAccount(account: Account)

    suspend fun deleteAccount()

}